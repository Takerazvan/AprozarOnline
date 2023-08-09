package com.codecool.backend.fileStorage;

import com.codecool.backend.exception.NullRequestException;
import com.codecool.backend.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.exception.SdkServiceException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.UUID;

@Slf4j
@Service
public class AWSImageServiceImpl implements ImageService {
    private final S3Client s3Client;
    private static final Logger logger = LoggerFactory.getLogger(AWSImageServiceImpl.class);
    private final ImageRepository imageRepository;
    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    public AWSImageServiceImpl(S3Client s3Client, ImageRepository imageRepository) {
        this.s3Client = s3Client;
        this.imageRepository = imageRepository;
    }

    @Override
    public String upload(MultipartFile file) throws IOException {
        try {
            logger.info("Uploading a PDF to S3 - {}", file.getOriginalFilename());
            if (file.isEmpty())
                throw new NullRequestException("Cannot upload empty file");


            String path = String.format("%s/%s", bucketName, UUID.randomUUID());
            String fileName = String.format("%s", file.getOriginalFilename());


            PutObjectResponse putObjectResult = s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(fileName)
                            .contentType(file.getContentType())
                            .contentLength(file.getSize())
                            .build(),
                    RequestBody.fromByteBuffer(ByteBuffer.wrap(file.getBytes())));
            String url = s3Client.utilities().getUrl(GetUrlRequest.builder().bucket(bucketName).key(fileName).build()).toString();
            logger.info("putObjectResult = " + putObjectResult);
            logger.info("reportUrl = " + url);

            Image image = new Image(fileName, path, file.getContentType(), file.getSize(), url);
            imageRepository.save(image);
            return url;
        }catch (SdkServiceException ase) {
            logger.error("Caught an AmazonServiceException, which " + "means your request made it "
                    + "to Amazon S3, but was rejected with an error response" + " for some reason.", ase);
            logger.info("Error Message:    " + ase.getMessage());
            logger.info("Key:       " + file.getOriginalFilename());
            throw ase;
        } catch (SdkClientException ace) {
            logger.error("Caught an AmazonClientException, which " + "means the client encountered "
                    + "an internal error while trying to " + "communicate with S3, "
                    + "such as not being able to access the network.", ace);
            logger.error("Error Message: {}, {}", file.getOriginalFilename(), ace.getMessage());
            throw ace;
        }
    }

    @Override
    public byte[] download(Long id){
        try {
            Image image = imageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Can't retrieve file id [%s]".formatted(id)));
            logger.info("Retrieving file from S3 for key: {}/{}", bucketName, image.getFileName());
            ResponseBytes<GetObjectResponse> s3Object = s3Client.getObject(
                    GetObjectRequest.builder().bucket(bucketName).key(image.getFileName()).build(), ResponseTransformer.toBytes());
            return s3Object.asByteArray();
        }catch (SdkClientException ase) {
            logger.error("Caught an AmazonServiceException, which " + "means your request made it "
                    + "to Amazon S3, but was rejected with an error response" + " for some reason: " + ase);
            throw ase;
        } catch (SdkServiceException ace) {
            logger.error("Caught an AmazonClientException, which " + "means the client encountered "
                    + "an internal error while trying to " + "communicate with S3, "
                    + "such as not being able to access the network: " +  ace);
            throw ace;
        }
    }

}

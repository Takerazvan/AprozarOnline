package com.codecool.backend.fileStorage;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
@Slf4j
@Service
public class ImageServiceImpl implements ImageService{
    @Value("${aws.bucket.name}")
    private String bucketName;
    private final AmazonS3Service s3Service;

    private final ImageRepository imageRepository;
@Autowired
    public ImageServiceImpl(AmazonS3Service s3Service, ImageRepository imageRepository) {
        this.s3Service = s3Service;
        this.imageRepository = imageRepository;
    }

    @Override
    public void upload(MultipartFile file) throws IOException {
        if (file.isEmpty())
            throw new IllegalStateException("Cannot upload empty file");

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String path = String.format("%s/%s", bucketName, UUID.randomUUID());
        String fileName = String.format("%s", file.getOriginalFilename());


        PutObjectResult putObjectResult = s3Service.upload(
                path, fileName, Optional.of(metadata), file.getInputStream());

        imageRepository.save(new Image(
                fileName, path, putObjectResult.getMetadata().getVersionId()
        ));

    }

    @Override
    public byte[] download (Long id) throws
            IOException{
        Image image = imageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        S3Object s3Object= s3Service.download(image.getFilePath(),image.getFileName());
        String contentType = s3Object.getObjectMetadata().getContentType();
        var bytes = s3Object.getObjectContent().readAllBytes();
return bytes;
    }

//    @Override
//    public List<Image> list() {
//        return null;
//    }
}

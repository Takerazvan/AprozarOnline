package com.codecool.backend.fileStorage;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;


@Service
public class S3Service {
    @Autowired
    private ImageRepository repository;

//    @Autowired
//    private AmazonS3 amazonS3;
//
//    @Value("${aws.s3.bucket}")
//    private String bucketName;
//
//    public String generatePreSignedUrl(String filePath, HttpMethod http) {
//
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date());
//        cal.add(Calendar.MINUTE,2);
//        return amazonS3.generatePresignedUrl(bucketName,filePath,cal.getTime(),http).toString();
//    }

    public String uploadImage(MultipartFile file) throws IOException {
        Image imageData = repository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return file.getOriginalFilename();
        }
        return null;
    }



    public byte[] downloadImage(String fileName) {
        Optional<Image> dbImage = repository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImage.get().getImageData());
        return images;
    }
}

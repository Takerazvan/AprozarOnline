package com.codecool.backend.fileStorage;

import com.amazonaws.services.s3.model.S3Object;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ImageService {
        public Image upload(MultipartFile file) throws IOException;
        public byte[] download(Long id) throws
                IOException;
    List<byte[]> listByUser(Long id);
    }


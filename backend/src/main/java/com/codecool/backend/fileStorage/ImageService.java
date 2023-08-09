package com.codecool.backend.fileStorage;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
        String upload(MultipartFile file) throws IOException;
        byte[] download(Long id) throws
                IOException;
//    List<byte[]> listByUser(Long id);
    }


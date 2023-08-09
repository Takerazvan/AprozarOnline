package com.codecool.backend.fileStorage;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_PATH")
    private String filePath;
    private String contentType;
    private long contentSize;
    private String url;

    public Image(String fileName, String filePath, String contentType, long contentSize, String url) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.contentType = contentType;
        this.contentSize = contentSize;
        this.url = url;
    }
}

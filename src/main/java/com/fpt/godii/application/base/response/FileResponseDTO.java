package com.fpt.godii.application.base.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class FileResponseDTO {
    private String fileName;
    private String contentType;
    private String url;
    private Timestamp createdDate;
}

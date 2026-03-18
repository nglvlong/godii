package com.fpt.godii.application.infrastructure.service;

import com.fpt.godii.application.base.response.FileResponseDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    ResponseEntity<FileResponseDTO> uploadFile(MultipartFile file);

    ResponseEntity<InputStreamResource> downloadFile(String path);

    byte[] downloadToByteArray(String path);
}

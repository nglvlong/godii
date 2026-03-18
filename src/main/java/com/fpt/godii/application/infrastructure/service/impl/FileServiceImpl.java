package com.fpt.godii.application.infrastructure.service.impl;

import com.fpt.godii.application.base.response.FileResponseDTO;
import com.fpt.godii.application.exception.FOBadRequestException;
import com.fpt.godii.application.infrastructure.service.FileService;
import com.fpt.godii.application.utils.APIUtil;
import com.fpt.godii.application.utils.MinIOUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final MinIOUtil minIOUtil;
    Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public ResponseEntity<FileResponseDTO> uploadFile(MultipartFile file) {
        String BUCKET_NAME = "esign";
        return new ResponseEntity<>(minIOUtil.uploadFile(file, BUCKET_NAME), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InputStreamResource> downloadFile(String path) {
        InputStream inputStream = minIOUtil.downloadFile(path);

        String fileName = Paths.get(path).getFileName().toString();
        HttpHeaders header = APIUtil.fileHeader(fileName);
        return new ResponseEntity<>(new InputStreamResource(inputStream), header, HttpStatus.OK);
    }

    @Override
    public byte[] downloadToByteArray(String path) {
        InputStream inputStream = minIOUtil.downloadFile(path);
        try {
            return inputStream.readAllBytes();
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new FOBadRequestException("Can't read file");
        }
    }
}

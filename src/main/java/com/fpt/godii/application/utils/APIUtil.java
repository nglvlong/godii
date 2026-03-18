package com.fpt.godii.application.utils;

import org.apache.tika.Tika;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;

public class APIUtil {
    public static HttpHeaders fileHeader(String fileName)
    {
        HttpHeaders header = new HttpHeaders();
        Tika tika = new Tika();
        fileName = StringUtil.removeAccent(fileName);
        header.setContentType(MediaType.parseMediaType(tika.detect(fileName)));
        header.set("Content-disposition", "attachment; filename=" + fileName);
        header.set("fileName", fileName);
        header.set("message", "Export success, please check result file");
        header.set("code", "200");
        header.setAccessControlExposeHeaders(Collections.singletonList("*"));
        return header;
    }
}

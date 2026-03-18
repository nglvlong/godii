package com.fpt.godii.application.base.model;

import jakarta.mail.internet.MimeBodyPart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HttpEmailData {
    private List<String> to = new ArrayList<>();
    private List<String> cc = new ArrayList<>();
    private List<String> bcc = new ArrayList<>();
    private String subject;
    private String body;
    private String templatePath;
    private boolean isHTML;
    private List<MimeBodyPart> attachments = new ArrayList<>();
    private Map<String, Object> variables = new HashMap<>();
}
package com.bsep.smart.home.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailWithAttachmentDetails {
    private String recipient;
    private String message;
    private String subject;
    private File file;
}
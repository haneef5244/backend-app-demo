package com.berjaya.demoappapi.service;

import com.berjaya.demoappapi.obj.MailDTO;
import com.berjaya.demoappapi.obj.ModuleResp;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;

@Service
public interface MailServiceHostInterface {

    void processEmail(String recipient, MultipartFile file);
    void sendEmail(MailDTO mail, MultipartFile file) throws MessagingException, IOException;
}

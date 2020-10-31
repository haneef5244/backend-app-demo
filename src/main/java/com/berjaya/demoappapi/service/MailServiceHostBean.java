package com.berjaya.demoappapi.service;

import com.berjaya.demoappapi.obj.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailServiceHostBean implements MailServiceHostInterface {

    @Value("${spring.mail.username}")
    private String systemUsername;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public void processEmail(String recipient, MultipartFile file) {
        MailDTO mail = new MailDTO();
        mail.setFrom(systemUsername);
        mail.setMailTo(recipient);
        mail.setSubject("We Have Received Your Submission!");

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", recipient);
        model.put("sign", "Admin");
        mail.setProps(model);
        try {
            sendEmail(mail, file);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendEmail(MailDTO mail, MultipartFile file) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.addAttachment(file.getOriginalFilename(), file);

        Context context = new Context();
        context.setVariables(mail.getProps());

        String html = templateEngine.process("MailTemplate", context);

        helper.setTo(mail.getMailTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());

        emailSender.send(message);
    }
}

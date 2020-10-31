package com.berjaya.demoappapi.service;

import com.berjaya.demoappapi.dao.ExceptionDAO;
import com.berjaya.demoappapi.dao.UploadHistoryDAO;
import com.berjaya.demoappapi.entity.Exception;
import com.berjaya.demoappapi.entity.UploadHistory;
import com.berjaya.demoappapi.obj.ModuleReq;
import com.berjaya.demoappapi.obj.ModuleResp;
import com.berjaya.demoappapi.status.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Service
public class ModuleServiceHostBean implements ModuleServiceHostInterface {

    Logger logger = LoggerFactory.getLogger(ModuleServiceHostBean.class);

    @Autowired
    UploadHistoryDAO uploadHistoryDAO;

    @Autowired
    ExceptionDAO exceptionDAO;

    @Autowired
    MailServiceHostInterface mailService;

    @Override
    public ModuleResp processUploadRequest(MultipartFile file, ModuleReq req) {
        ModuleResp resp = new ModuleResp();
        try {
            UploadHistory uploadHistory = new UploadHistory();
            uploadHistory.setData(file.getBytes());
            uploadHistory.setFileName(file.getResource().getFilename());
            uploadHistory.setFileSize(file.getSize());
            uploadHistory.setMainType(req.getMainType());
            uploadHistory.setMajorType(req.getMajorType());
            uploadHistory.setModuleName(req.getModuleName());
            uploadHistory.setEmail(req.getEmail());
            uploadHistoryDAO.save(uploadHistory);

            mailService.processEmail(req.getEmail(), file);
            resp.setCode(Status.SUCCESS.code);
            resp.setMessage(Status.SUCCESS.message);
        } catch (IOException e) {
            if (e instanceof IOException) {
                Exception exception = new Exception();
                exception.setReason(e.toString());
                exception.setEmail(req.getEmail());
                exceptionDAO.save(exception);
            } else {
                // TODO LOG DB ERROR
                logger.error("");
            }
            resp.setCode(Status.INTERNAL_SERVER_ERROR.code);
            resp.setMessage(Status.INTERNAL_SERVER_ERROR.message);
        }
        return resp;
    }

}

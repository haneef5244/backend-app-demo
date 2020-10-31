package com.berjaya.demoappapi.service;

import com.berjaya.demoappapi.obj.ModuleReq;
import com.berjaya.demoappapi.obj.ModuleResp;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ModuleServiceHostInterface {

    ModuleResp processUploadRequest(MultipartFile file, ModuleReq req);
}

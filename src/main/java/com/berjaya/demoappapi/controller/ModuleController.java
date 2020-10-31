package com.berjaya.demoappapi.controller;

import com.berjaya.demoappapi.obj.ModuleReq;
import com.berjaya.demoappapi.obj.ModuleResp;
import com.berjaya.demoappapi.service.ModuleServiceHostInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ModuleController {

    @Autowired
    ModuleServiceHostInterface service;

    @RequestMapping(value = "/module/register", method = RequestMethod.POST, consumes = "multipart/form-data;charset=UTF-8")
    public ResponseEntity<ModuleResp> handleFileUpload(@RequestParam("File1") MultipartFile file,
                                                       @RequestPart("request") ModuleReq req) {
        return ResponseEntity.ok(service.processUploadRequest(file, req));
    }

}

package com.service;

import com.entity.Result;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    Result fileTransfer(MultipartFile file);
    Result fileRead(String path);
}

package com.leyou.upload.controller;

import com.leyou.upload.service.UploadService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 路径：/upload/image
 * 方式：post
 * 参数：file: (二进制)
 * 返回值：上传成功后得到的文件的url路径，也就是返回String
 */
@Controller
@RequestMapping
public class UploadController {
    @Autowired
    private UploadService uploadService;

    /**
     * 图片上传
     *
     * @param file
     * @return
     */
    @PostMapping("/upload/image")
    public ResponseEntity<String> uploadImage(@RequestParam(value = "file", required = false) MultipartFile file) {
        String url = this.uploadService.upload(file);
        if (StringUtils.isBlank(url)) {
            return ResponseEntity.badRequest().build();
        }
        //return ResponseEntity.ok(url);
        return ResponseEntity.status(HttpStatus.CREATED).body(url);
    }

}

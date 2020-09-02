package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final FileService fileService;

  public FileController(FileService fileService) {
    this.fileService = fileService;
  }

  @PostMapping("/file")
  public String save(@RequestParam("fileUpload") MultipartFile multipartFile) throws IOException {
    File file = new File();
    file.setContentType(multipartFile.getContentType());
    file.setName(multipartFile.getOriginalFilename());
    file.setData(multipartFile.getBytes());
    file.setSize(String.valueOf(multipartFile.getSize()));
    fileService.save(file);
    return "result";
  }

  @GetMapping("/getFile/{id}")
  @ResponseBody
  public ResponseEntity<byte[]> find(@PathVariable int id) {
    File file = fileService.find(id);
    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(file.getContentType()))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
        .body(file.getData());
  }

  @GetMapping("/deleteFile/{id}")
  public String delete(@PathVariable int id) {
    fileService.delete(id);
    return "result";
  }
}

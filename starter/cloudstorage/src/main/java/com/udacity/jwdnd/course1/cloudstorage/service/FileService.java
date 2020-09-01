package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.exception.FileAlreadyPresentException;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.file.FileAlreadyExistsException;
import java.util.Arrays;
import java.util.List;

@Service
public class FileService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final FileMapper fileMapper;
  private final UserService userService;

  public FileService(FileMapper fileMapper, UserService userService) {
    this.fileMapper = fileMapper;
    this.userService = userService;
  }

  public List<File> findAll() {
    int userId = userService.getCurrentUserId();
    logger.info("Current userId: " + userId);
    List<File> files = fileMapper.findByUserId(userId);
    logger.info("Files from db: " + Arrays.toString(files.toArray()));
    return files;
  }

  public void save(File file) {
    logger.info("Save file: " + file);
    file.setUserId(userService.getCurrentUserId());
    List<File> files = fileMapper.findByUserId(file.getUserId());
    boolean anyMatch =
        files.stream().anyMatch(fileFromDb -> fileFromDb.getName().equals(file.getName()));
    if (anyMatch) {
      throw new FileAlreadyPresentException();
    }
    logger.info("Saving updated file: " + file);
    fileMapper.save(file);
  }

  public void delete(int id) {
    logger.info("Delete id: " + id);
    fileMapper.delete(id);
  }

  public File find(int id) {
    return fileMapper.findById(id);
  }
}

package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.exception.FileAlreadyPresentException;
import com.udacity.jwdnd.course1.cloudstorage.exception.FileEmptyException;
import com.udacity.jwdnd.course1.cloudstorage.exception.FileNotFoundException;
import com.udacity.jwdnd.course1.cloudstorage.exception.NoteNotFoundException;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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

  public File find(int id) {
    return fileMapper.findById(id);
  }

  public List<File> findAll() {
    int userId = userService.getCurrentUserId();
    return fileMapper.findByUserId(userId);
  }

  public void save(File file) {
    if (file.getName().isEmpty()) {
      throw new FileEmptyException();
    }
    file.setUserId(userService.getCurrentUserId());
    List<File> files = fileMapper.findByUserId(file.getUserId());
    boolean anyMatch =
        files.stream().anyMatch(fileFromDb -> fileFromDb.getName().equalsIgnoreCase(file.getName()));
    if (anyMatch) {
      throw new FileAlreadyPresentException();
    }
    fileMapper.save(file);
  }

  public void delete(int id) {
    isPresent(id);
    fileMapper.delete(id);
  }

  private void isPresent(int id) {
    List<File> files = fileMapper.findByUserId(userService.getCurrentUserId());
    boolean noneMatch =
            files.stream().noneMatch(filesFromDb -> filesFromDb.getId() == id);
    if (noneMatch) {
      throw new FileNotFoundException();
    }
  }
}

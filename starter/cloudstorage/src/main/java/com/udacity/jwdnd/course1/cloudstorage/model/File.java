package com.udacity.jwdnd.course1.cloudstorage.model;

import java.sql.Blob;
import java.util.Arrays;

public class File {

  private int id;
  private String name;
  private String contentType;
  private String size;
  private int userId;
  private byte[] data;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "File{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", contentType='" + contentType + '\'' +
            ", size='" + size + '\'' +
            ", userId=" + userId +
            '}';
  }
}

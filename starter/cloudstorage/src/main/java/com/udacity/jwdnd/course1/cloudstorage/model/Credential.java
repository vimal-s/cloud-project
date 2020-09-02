package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credential {

  private int id;
  private String url;
  private String username;
  private String key;
  private String password;
  private int userId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "Credential{" +
              "id=" + id +
              ", url='" + url + '\'' +
              ", username='" + username + '\'' +
              ", key='" + key + '\'' +
              ", password='" + password + '\'' +
              ", userId=" + userId +
            '}';
  }
}

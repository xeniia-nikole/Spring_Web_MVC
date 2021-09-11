package ru.netology.model;

public class Post {
  private int id;
  private String content;
  private boolean isRemoved = false;

  public Post() {
  }

  public Post(int id, String content) {
    this.id = id;
    this.content = content;
  }

  public int getID() {
    return id;
  }

  public void setID(int id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public boolean getIsRemoved() {
    return isRemoved;
  }

  public void setIsRemoved(boolean removed) {
    isRemoved = removed;
  }
}

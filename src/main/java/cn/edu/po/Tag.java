package cn.edu.po;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.springframework.stereotype.Component;

@Component
public class Tag {
  private Long id;
  private String name;
  private int size;
  private List<Blog> blogs=new ArrayList<>();
  @Override
  public String toString() {
    return "Tag{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getSize() {
    return blogs.size();
  }

  public List<Blog> getBlogs() {
    return blogs;
  }

  public void setBlogs(List<Blog> blogs) {
    this.blogs = blogs;
  }

  public Tag() {
  }
  public Tag(String name) {
    this.name=name;
  }
}

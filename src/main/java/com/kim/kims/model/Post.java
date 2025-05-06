package com.kim.kims.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private String title;
  private String content;
}

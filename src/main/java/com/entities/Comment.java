package com.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
public class Comment {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Setter
    private String content;
    @Getter
    private Long authorId;
    private LocalDate createAt = LocalDate.now();

    public Comment(String content, Long authorId) {
        this.content = content;
        this.authorId = authorId;
    }

    public Comment() {

    }
}

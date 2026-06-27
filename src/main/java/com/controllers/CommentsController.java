package com.controllers;

import com.dtos.CommentIdDto;
import com.dtos.EditCommentDto;
import com.repositories.CommentsRepository;
import com.services.CommentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {
    private final CommentsService commentsService;

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @PatchMapping
    public ResponseEntity<?> editComment(@RequestBody EditCommentDto comment){
        commentsService.editComment(comment);
        return ResponseEntity.ok().body(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id){
        commentsService.deleteComment(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/author")
    public ResponseEntity<?> getAuthorId(@PathVariable Long id){
        return ResponseEntity.ok().body(commentsService.getAuthorId(id));
    }
}

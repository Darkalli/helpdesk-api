package com.services;

import com.dtos.CommentIdDto;
import com.dtos.EditCommentDto;
import com.repositories.CommentsRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {

    private final CommentsRepository commentRepository;

    public CommentsService(CommentsRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void editComment(EditCommentDto comment){
        commentRepository.findById(comment.commentId()).ifPresent(ticket -> {
            ticket.setContent(comment.newContent());
        });
    }

    public void deleteComment(Long id){
        if(commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        }
    }
    public Long getAuthorId(Long id){
        return commentRepository.authorId(id);
    }
}


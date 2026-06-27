package com.dtos;

public record AddCommentDto(Long ticketId, String content, Long authorId) {
}

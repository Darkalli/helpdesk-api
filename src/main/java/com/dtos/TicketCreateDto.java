package com.dtos;

import com.entities.Category;
import com.entities.Client;
import com.enums.Priority;
import com.enums.Status;

public record TicketCreateDto(String title, String description, Status status, Priority priority, Long categoryId, Long clientId) {
}

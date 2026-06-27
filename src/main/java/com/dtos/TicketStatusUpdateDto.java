package com.dtos;

import com.enums.Status;

public record TicketStatusUpdateDto(long ticketId, Status status) {
}

package com.controllers;

import com.dtos.*;
import com.services.TicketsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketsController {

    private final TicketsService ticketsService;

    public TicketsController(TicketsService ticketsService) {
        this.ticketsService = ticketsService;
    }

    @PostMapping
    public ResponseEntity<?> createTicket(@RequestBody TicketCreateDto ticket){
        ticketsService.createTicket(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }

    @PatchMapping("/link")
    public ResponseEntity<?> linkAgent(@RequestBody LinkAgentDto agent){
        ticketsService.linkAgent(agent);
        return ResponseEntity.ok().body(agent);
    }

    @PatchMapping("/status")
    public ResponseEntity<?> updateStatus(@RequestBody TicketStatusUpdateDto status){
        ticketsService.updateStatus(status);
        return ResponseEntity.ok().body(status);
    }

    @PatchMapping("/close")
    public ResponseEntity<?> closeTicket(@RequestBody TicketCloseDto close){
        ticketsService.closeTicket(close);
        return ResponseEntity.ok().body(close);
    }

    @PatchMapping("/comment")
    public ResponseEntity<?> addComment(@RequestBody AddCommentDto comment){
        ticketsService.addComment(comment);
        return ResponseEntity.ok().body(comment);
    }

    @PatchMapping("/change")
    public ResponseEntity<?> changeAgent(@RequestBody LinkAgentDto agent){
        ticketsService.changeAgent(agent);
        return ResponseEntity.ok().body(agent);
    }

}

package com.services;

import com.dtos.*;
import com.entities.*;
import com.enums.Status;
import com.repositories.CategoriesRepository;
import com.repositories.TicketsRepository;
import com.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TicketsService {

    private final UsersService usersService;
    private final TicketsRepository ticketRepository;
    private final UsersRepository usersRepository;
    private final CategoriesRepository categoryRepository;

    public TicketsService(UsersService usersService, TicketsRepository ticketRepository, UsersRepository usersRepository, CategoriesRepository categoryRepository) {
        this.usersService = usersService;
        this.ticketRepository = ticketRepository;
        this.usersRepository = usersRepository;
        this.categoryRepository = categoryRepository;
    }

    public void createTicket(TicketCreateDto ticket){
        User client = usersService.getById(ticket.clientId());
            Category category = categoryRepository.getReferenceById(ticket.categoryId());
            Ticket newTicket = new Ticket(ticket.title(), ticket.description(), ticket.status(), ticket.priority(),category, (Client) client);
            ticketRepository.save(newTicket);
    }

    public void linkAgent(LinkAgentDto agentLink){
        if (ticketRepository.existsById(agentLink.ticketId()) && usersRepository.existsById(agentLink.agentId())) {
            Ticket ticket = ticketRepository.getReferenceById(agentLink.ticketId());
            User agent = usersService.getById(agentLink.agentId());
            ticket.setAgent((Agent) agent);
            ticketRepository.save(ticket);
        }
    }

    public void updateStatus (TicketStatusUpdateDto status){
        if (Status.isValid(status.status())){
            ticketRepository.findById(status.ticketId()).ifPresent(ticket -> {
                ticket.setStatus(status.status());
                ticketRepository.save(ticket);
            });
        }
    }

    public void closeTicket(TicketCloseDto close){
        if (ticketRepository.existsById(close.ticketId())){
            Ticket ticket = ticketRepository.getReferenceById(close.ticketId());
            ticket.setStatus(Status.CONCLUDED);
            ticket.setClosedAt(LocalDate.now());
            ticket.setFinalNote(close.finalNote());
        }
    }

    public void addComment(AddCommentDto comment){
        new Comment(comment.content(), comment.authorId());
    }

    public void changeAgent(LinkAgentDto agent){
        if (usersRepository.existsById(agent.agentId())){
            ticketRepository.findById(agent.ticketId()).ifPresent(ticket -> {
                ticket.setAgent((Agent) usersService.getById(agent.agentId()));
            });
        }
    }
}

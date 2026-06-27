package com.entities;

import com.enums.Priority;
import com.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class Ticket {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private String title;
    @Getter
    private String description;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Status status;
    @Getter
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createAt = LocalDate.now();
    @Getter
    @Setter
    private LocalDate closedAt;
    @Setter
    @ManyToOne
    @JoinColumn(name = "category_id")
    @Getter
    private Category category;
    @Setter
    @ManyToOne
    @JoinColumn(name = "client_id")
    @Getter
    private Client client;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;
    @Getter
    @Setter
    @OneToMany
    private List<Comment> comments;
    @Getter
    @Setter
    private String finalNote;

    public Ticket(String title, String description, Status status, Priority priority, Category category, Client client) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.category = category;
        this.client = client;
    }

    public Ticket() {

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

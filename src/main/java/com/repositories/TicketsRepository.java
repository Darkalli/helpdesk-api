package com.repositories;

import com.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketsRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByCategoryId(Long categoryId);
}

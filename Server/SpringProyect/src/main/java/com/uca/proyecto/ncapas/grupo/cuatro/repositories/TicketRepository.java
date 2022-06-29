package com.uca.proyecto.ncapas.grupo.cuatro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Ticket;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.User;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{
	Ticket findOneById(int id);
	List<Ticket> findByUser(User user);
}

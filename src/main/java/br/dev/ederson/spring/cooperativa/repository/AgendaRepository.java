package br.dev.ederson.spring.cooperativa.repository;

import br.dev.ederson.spring.cooperativa.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}

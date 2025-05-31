package br.dev.ederson.spring.cooperativa.repository;

import br.dev.ederson.spring.cooperativa.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}

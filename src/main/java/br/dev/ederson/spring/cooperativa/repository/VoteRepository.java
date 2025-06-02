package br.dev.ederson.spring.cooperativa.repository;

import br.dev.ederson.spring.cooperativa.model.Agenda;
import br.dev.ederson.spring.cooperativa.model.Associate;
import br.dev.ederson.spring.cooperativa.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findByAssociate(Associate associate);

    List<Vote> findByAgenda(Agenda agenda);

    @Query("SELECT count (v.id) as qtdVotesNotApproved FROM Vote v WHERE v.approve = false")
    int countVotesNotApproved(Agenda agenda);

    @Query("SELECT count (v.id) as qtdVotes FROM Vote v")
    int countVotesForAgenda(Agenda agenda);

}

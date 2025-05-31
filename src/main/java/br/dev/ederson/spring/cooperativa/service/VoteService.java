package br.dev.ederson.spring.cooperativa.service;

import br.dev.ederson.spring.cooperativa.model.Agenda;
import br.dev.ederson.spring.cooperativa.model.Associate;
import br.dev.ederson.spring.cooperativa.model.Vote;
import br.dev.ederson.spring.cooperativa.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VoteService {
    @Autowired
    private VoteRepository voteRepository;

    public void registerVote(Associate associate, Agenda agenda, boolean approve) {
        Vote vote = new Vote();

        vote.setAssociate(associate);
        vote.setAgenda(agenda);
        vote.setApprove(approve);
        vote.setVoteDate(LocalDateTime.now());

        voteRepository.save(vote);
    }
}

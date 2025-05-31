package br.dev.ederson.spring.cooperativa.service;

import br.dev.ederson.spring.cooperativa.model.Agenda;
import br.dev.ederson.spring.cooperativa.model.Session;
import br.dev.ederson.spring.cooperativa.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    public Session save(Session associate) {
        return sessionRepository.save(associate);
    }

    public Session getById(Long agendaId) {
        return sessionRepository.findById(agendaId).orElse(null);
    }

    public void addAgenda(Session session, Agenda agenda) {
        session.getAgendas().add(agenda);
        sessionRepository.save(session);
    }
}

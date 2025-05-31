package br.dev.ederson.spring.cooperativa.service;

import br.dev.ederson.spring.cooperativa.exception.BadRequestException;
import br.dev.ederson.spring.cooperativa.exception.NotFoundException;
import br.dev.ederson.spring.cooperativa.model.Agenda;
import br.dev.ederson.spring.cooperativa.model.Session;
import br.dev.ederson.spring.cooperativa.repository.SessionRepository;
import br.dev.ederson.spring.cooperativa.util.ProjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    public Session getById(Long agendaId) {
        return sessionRepository.findById(agendaId).orElse(null);
    }

    private void addAgenda(Session session, Agenda agenda) {
        session.getAgendas().add(agenda);
        sessionRepository.save(session);
    }

    public Session createSession(Session session) {
        if (session.getDurationTime() <= 0)
            session.setDurationTime(1);

        return sessionRepository.save(session);
    }

    public void addAgendaToSession(Agenda agenda, Session session) throws BadRequestException {
        if (!ProjectUtil.isEmpty(session.getAgendas()) && session.getAgendas().contains(agenda))
            throw new BadRequestException("Agenda " + agenda.getId() + " is already in the Session provided!");

        addAgenda(session, agenda);
    }

    public Session startSession(Long sessionId) throws BadRequestException, NotFoundException {
        Session session = sessionRepository.findById(sessionId).orElse(null);

        if (session == null)
            throw new NotFoundException("Session " + sessionId + " not found!");

        if (ProjectUtil.isEmpty(session.getAgendas()))
            throw new BadRequestException("Session Agendas must be specified before start the vote process!");

        session.setStartDate(LocalDateTime.now());
        System.out.println("Iniciando sessão " + session.getId() + ".\nData: " + LocalDateTime.now());
        initializeTimer(session);
        return sessionRepository.save(session);
    }

    private void initializeTimer(Session session) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                session.setEndDate(LocalDateTime.now());
                System.out.println("Encerrando sessão " + session.getId() + ".\nData: " + LocalDateTime.now());
                sessionRepository.save(session);
            }
        }, (session.getDurationTime() * 1000L));
    }

    public boolean isSessionOpenToVote(Session session) {
        //Controle implementado assim para ter mais exatidão
        LocalDateTime endDate = session.getStartDate().plusMinutes(session.getDurationTime());
        return LocalDateTime.now().isBefore(endDate);
    }
}

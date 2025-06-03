package br.dev.ederson.spring.cooperativa.service;

import br.dev.ederson.spring.cooperativa.exception.BadRequestException;
import br.dev.ederson.spring.cooperativa.model.Agenda;
import br.dev.ederson.spring.cooperativa.model.Associate;
import br.dev.ederson.spring.cooperativa.model.Session;
import br.dev.ederson.spring.cooperativa.repository.AgendaRepository;
import br.dev.ederson.spring.cooperativa.repository.AssociateRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociateService {
    @Autowired
    private AssociateRepository associateRepository;
    @Autowired
    private AgendaRepository agendaRepository;

    public Associate getById(Long agendaId) {
        return associateRepository.findById(agendaId).orElse(null);
    }

    public Associate createAssociate(Associate associate) throws BadRequestException {
        if (StringUtils.isBlank(associate.getName()))
            throw new BadRequestException("Associate name must be specified!");

        if (StringUtils.isBlank(associate.getDocument()))
            throw new BadRequestException("Associate document must be specified!");

        return associateRepository.save(associate);
    }

    public void addAgendaToSession(Agenda agenda, Session session) throws BadRequestException {
        if (agenda.getSession() != null)
            throw new BadRequestException("This Agenda is already attached in one Session!");

        addAgenda(session, agenda);
    }

    private void addAgenda(Session session, Agenda agenda) {
        agenda.setSession(session);
        agendaRepository.save(agenda);
    }
}

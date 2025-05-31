package br.dev.ederson.spring.cooperativa.service;

import br.dev.ederson.spring.cooperativa.AgendaStatus;
import br.dev.ederson.spring.cooperativa.exception.BadRequestException;
import br.dev.ederson.spring.cooperativa.model.Agenda;
import br.dev.ederson.spring.cooperativa.repository.AgendaRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AgendaService {
    @Autowired
    private AgendaRepository agendaRepository;

    public Agenda getById(Long agendaId) {
        return agendaRepository.findById(agendaId).orElse(null);
    }

    public Agenda createAgenda(Agenda agenda) throws BadRequestException {
        if (StringUtils.isBlank(agenda.getDescription()))
            throw new BadRequestException("Agenda description must be specified!");

        agenda.setRegistrationDate(LocalDateTime.now());
        agenda.setStatus(AgendaStatus.TO_VOTE);

        return agendaRepository.save(agenda);
    }

}

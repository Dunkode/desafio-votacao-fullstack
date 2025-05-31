package br.dev.ederson.spring.cooperativa.service;

import br.dev.ederson.spring.cooperativa.model.Agenda;
import br.dev.ederson.spring.cooperativa.model.Associate;
import br.dev.ederson.spring.cooperativa.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaService {
    @Autowired
    private AgendaRepository agendaRepository;

    public Agenda save(Agenda associate) {
        return agendaRepository.save(associate);
    }

    public Agenda getById(Long agendaId) {
        return agendaRepository.findById(agendaId).orElse(null);
    }
}

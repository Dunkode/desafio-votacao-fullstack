package br.dev.ederson.spring.cooperativa.controller;


import br.dev.ederson.spring.cooperativa.AgendaStatus;
import br.dev.ederson.spring.cooperativa.exception.BadRequestException;
import br.dev.ederson.spring.cooperativa.exception.NotFoundException;
import br.dev.ederson.spring.cooperativa.model.Agenda;
import br.dev.ederson.spring.cooperativa.model.Associate;
import br.dev.ederson.spring.cooperativa.model.Response;
import br.dev.ederson.spring.cooperativa.model.Session;
import br.dev.ederson.spring.cooperativa.service.AgendaService;
import br.dev.ederson.spring.cooperativa.service.AssociateService;
import br.dev.ederson.spring.cooperativa.service.SessionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class AssemblyRestController {

    @Autowired private AgendaService agendaService;
    @Autowired private AssociateService associateService;
    @Autowired private SessionService sessionService;


    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }

    @PostMapping("/createAssociate")
    public Response createAssociate(@RequestBody Associate associate) throws BadRequestException {
        if (StringUtils.isBlank(associate.getName()))
            throw new BadRequestException("Associate name must be specified!");

        if (StringUtils.isBlank(associate.getDocument()))
            throw new BadRequestException("Associate document must be specified!");

        associate = associateService.save(associate);

        return new Response("Associate " + associate.getName() + " registered successfully!");
    }

    @PostMapping("/createAgenda")
    public Response createAgenda(@RequestBody Agenda agenda) throws BadRequestException {
        if (StringUtils.isBlank(agenda.getDescription()))
            throw new BadRequestException("Agenda description must be specified!");

        agenda.setRegistrationDate(LocalDateTime.now());
        agenda.setStatus(AgendaStatus.TO_VOTE);
        agenda = agendaService.save(agenda);

        return new Response("Agenda " + agenda.getDescription() + " registered successfully!");
    }

    @PostMapping("/createSession")
    public Response createSession(@RequestBody Session session) throws BadRequestException {
        if (session.getDurationTime() <= 0)
            throw new BadRequestException("Session duration time must be specified (in minutes)!");

        session = sessionService.save(session);

        return new Response("Session " + session.getId() + " registered successfully!");
    }


    @PostMapping("/addAgendaToSession")
    public Response addAgendaToSession(@RequestBody Long agendaId, Long sessionId) throws BadRequestException, NotFoundException {
        if (agendaId == null)
            throw new BadRequestException("An Agenda must be specified!");

        if (sessionId == null)
            throw new BadRequestException("An Agenda must be specified!");

        Agenda agenda = agendaService.getById(agendaId);
        if (agenda == null)
            throw new NotFoundException("Agenda " + agendaId + " not found!");

        Session session = sessionService.getById(agendaId);
        if (session == null)
            throw new NotFoundException("Session " + sessionId + " not found!");

        sessionService.addAgenda(session, agenda);

        return new Response("Agenda has added to Session successfully!");
    }

    @PostMapping("/startSession")
    public String startSession(@RequestBody Session session) throws BadRequestException {
        if (session.getAgendas() == null || !session.getAgendas().isEmpty())
            throw new BadRequestException("Session Agendas must be specified!");

        return "Session " + session.getId() + " started and will last for " + session.getDurationTime() + " minutes.";
    }

}

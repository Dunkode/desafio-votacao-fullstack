package br.dev.ederson.spring.cooperativa.controller;


import br.dev.ederson.spring.cooperativa.exception.BadRequestException;
import br.dev.ederson.spring.cooperativa.exception.NotFoundException;
import br.dev.ederson.spring.cooperativa.model.Agenda;
import br.dev.ederson.spring.cooperativa.model.Associate;
import br.dev.ederson.spring.cooperativa.model.Response;
import br.dev.ederson.spring.cooperativa.model.Session;
import br.dev.ederson.spring.cooperativa.service.AgendaService;
import br.dev.ederson.spring.cooperativa.service.AssociateService;
import br.dev.ederson.spring.cooperativa.service.SessionService;
import br.dev.ederson.spring.cooperativa.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssemblyRestController {

    @Autowired private AgendaService agendaService;
    @Autowired private AssociateService associateService;
    @Autowired private SessionService sessionService;
    @Autowired private VoteService voteService;


    @PostMapping("/createAssociate")
    public Response createAssociate(@RequestBody Associate associate) throws BadRequestException {
        associate = associateService.createAssociate(associate);
        return new Response("Associate " + associate.getName() + " registered successfully!");
    }

    @PostMapping("/createAgenda")
    public Response createAgenda(@RequestBody Agenda agenda) throws BadRequestException {
        agenda = agendaService.createAgenda(agenda);
        return new Response("Agenda " + agenda.getDescription() + " registered successfully!");
    }

    @PostMapping("/createSession")
    public Response createSession(@RequestBody Session session) {
        session = sessionService.createSession(session);
        return new Response("Session " + session.getId() + " registered successfully!");
    }

    @PostMapping("/addAgendaToSession")
    public Response addAgendaToSession(@Param("agendaId") Long agendaId, @Param("sessionId") Long sessionId) throws BadRequestException, NotFoundException {
        Agenda agenda = agendaService.getById(agendaId);
        if (agenda == null)
            throw new NotFoundException("Agenda " + agendaId + " not found!");

        Session session = sessionService.getById(agendaId);
        if (session == null)
            throw new NotFoundException("Session " + sessionId + " not found!");

        sessionService.addAgendaToSession(agenda, session);
        return new Response("Agenda has added to Session successfully!");
    }

    @PostMapping("/startSession")
    public Response startSession(@Param("sessionId") Long sessionId) throws BadRequestException, NotFoundException {
        Session session = sessionService.startSession(sessionId);
        return new Response("Session " + session.getId() + " started and will last for " + session.getDurationTime() + " minutes.");
    }

    @PostMapping("/vote")
    public Response vote(@Param("associateId") Long associateId, @Param("agendaId") Long agendaId, @Param("approve") Boolean approve) throws BadRequestException, NotFoundException {
        Associate associate = associateService.getById(agendaId);
        if (associate == null)
            throw new NotFoundException("Associate " + associateId + " not found!");

        Agenda agenda = agendaService.getById(agendaId);
        if (agenda == null)
            throw new NotFoundException("Agenda " + agendaId + " not found!");

        if (approve == null)
            throw new BadRequestException("You need to specify your Vote on this Agenda.");

        voteService.registerVote(associate, agenda, approve);
        return new Response("You voted to "+ (approve ? "" : "not") + " aprove Agenda " + agendaId + ".");
    }

}

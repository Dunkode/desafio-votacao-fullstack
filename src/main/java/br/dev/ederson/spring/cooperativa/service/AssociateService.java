package br.dev.ederson.spring.cooperativa.service;

import br.dev.ederson.spring.cooperativa.model.Associate;
import br.dev.ederson.spring.cooperativa.repository.AssociateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociateService {
    @Autowired
    private AssociateRepository associateRepository;

    public Associate save(Associate associate) {
        return associateRepository.save(associate);
    }
}

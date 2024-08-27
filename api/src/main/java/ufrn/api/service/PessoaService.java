package ufrn.api.service;

import org.springframework.stereotype.Service;

import ufrn.api.domain.Pessoa;
import ufrn.api.repository.PessoaRepository;
import ufrn.api.service.generic.GenericCrudService;


@Service
public class PessoaService extends GenericCrudService<Pessoa, Long, PessoaRepository>{
    public PessoaService(PessoaRepository repository){
        super(repository);
    }
}

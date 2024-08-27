package ufrn.api.service;

import org.springframework.stereotype.Service;

import ufrn.api.domain.Camisa;
import ufrn.api.service.generic.GenericCrudService;
import ufrn.api.repository.CamisaRepository;


@Service
public class CamisaService extends GenericCrudService<Camisa, Long, CamisaRepository>{
    public CamisaService(CamisaRepository repository){
        super(repository);
    }
}

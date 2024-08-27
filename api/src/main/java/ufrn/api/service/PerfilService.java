package ufrn.api.service;

import org.springframework.stereotype.Service;
import ufrn.api.domain.*;
import ufrn.api.repository.PerfilRepository;
import ufrn.api.service.generic.GenericCrudService;
@Service
public class PerfilService extends GenericCrudService<Perfil, Long, PerfilRepository>{
    public PerfilService(PerfilRepository repository){
        super(repository);
    }
}

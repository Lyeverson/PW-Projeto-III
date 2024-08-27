package ufrn.api.service;

import org.springframework.stereotype.Service;

import ufrn.api.domain.Pedido;
import ufrn.api.repository.PedidoRepository;
import ufrn.api.service.generic.GenericCrudService;

@Service
public class PedidoService extends GenericCrudService<Pedido, Long, PedidoRepository>{
    public PedidoService(PedidoRepository repository){
        super(repository);
    }
}

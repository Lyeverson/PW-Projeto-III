package ufrn.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufrn.api.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    
}
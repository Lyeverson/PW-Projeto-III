package ufrn.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufrn.api.domain.Camisa;

public interface CamisaRepository extends JpaRepository<Camisa, Long>{
    
}
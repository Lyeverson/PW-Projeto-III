package ufrn.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufrn.api.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
    
}

package ufrn.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufrn.api.domain.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long>{
    
}


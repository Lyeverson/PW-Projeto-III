package ufrn.api.repository;

import org.springframework.data.repository.CrudRepository;
import ufrn.api.domain.SecurityUser;

public interface SecurityUserRerpository extends CrudRepository<SecurityUser, Long> {
    SecurityUser findByUsername(String username);
}

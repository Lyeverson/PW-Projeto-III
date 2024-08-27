package ufrn.api.service.generic;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ufrn.api.service.interfaces.ICrudService;


import java.util.List;
import java.util.Optional;

public abstract class GenericCrudService<T, ID, REPO extends JpaRepository<T, ID>> implements ICrudService<T, ID> {

    private final REPO repository;

    public GenericCrudService(REPO repository) {
        this.repository = repository;
    }

    @Override
    public T create(T entity) {
        return repository.save(entity);
    }

    @Override
    public Page<T> listAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public T listById(ID id) {
        Optional<T> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("Objeto de id " + id + " not found");
    }

    @Override
    public void deleteById(ID id) {
        T entity = this.listById(id);
        this.repository.delete(entity);
    }

    @Override
    public T update(T entity, ID id) {
      
        return this.repository.saveAndFlush(entity);
    }
}

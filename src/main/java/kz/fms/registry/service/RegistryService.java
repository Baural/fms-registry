package kz.fms.registry.service;

import kz.fms.registry.entity.Registry;
import kz.fms.registry.repository.RegistryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author baur
 * @date on 09.07.2020
 */

@Service
@Transactional
public class RegistryService {

    private final RegistryRepository repository;

    public RegistryService(RegistryRepository repository) {
        this.repository = repository;
    }


    public List<Registry> findAll() {
        return repository.findAll();
    }

    public Registry add(Registry registry) {
        return repository.save(registry);
    }

    public Registry update(Registry registry) {
        return repository.save(registry);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }


    public Page findByParams(Long patientId, Long clinicId, PageRequest paging) {
        return repository.findByParams(patientId, clinicId, paging);
    }

    public Registry findById(Long id) {
        return repository.findById(id).get();
    }


}

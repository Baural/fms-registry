package kz.fms.registry.service;

import kz.fms.registry.entity.Clinic;
import kz.fms.registry.repository.ClinicRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author baur
 * @date on 09.07.2020
 */



@Service
@Transactional
public class ClinicService {
    private final ClinicRepository repository;

    public ClinicService(ClinicRepository repository) {
        this.repository = repository;
    }


    public List<Clinic> findAll() {
        return repository.findAll();
    }

    public Clinic add(Clinic category) {
        return repository.save(category);
    }

    public Clinic update(Clinic category){
        return repository.save(category);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Clinic findById(Long id){
        return repository.findById(id).get();
    }


    public List<Clinic> findByName(String name){
        return repository.findByName(name);
    }

}

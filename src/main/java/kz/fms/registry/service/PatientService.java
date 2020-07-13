package kz.fms.registry.service;


import kz.fms.registry.entity.Patient;
import kz.fms.registry.repository.PatientRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author baur
 * @date on 01.07.2020
 */

@Service
@Transactional
public class PatientService {
    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }


    public List<Patient> findAll() {
        return repository.findAll();
    }

    public Patient add(Patient category) {
        return repository.save(category);
    }

    public Patient update(Patient category){
        return repository.save(category);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public List<Patient> findByIin(String iin){
        return repository.findByIin(iin);
    }

    public Patient findById(Long id){
        return repository.findById(id).get();
    }

    public List<Patient> findAllByOrderByIinAsc(){
        return repository.findAllByOrderByIinAsc();
    }

}

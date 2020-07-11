package kz.fms.registry.service;
import kz.fms.registry.entity.Clinic;
import kz.fms.registry.repository.ClinicRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class ClinicService {

    private final ClinicRepository repository; // сервис имеет право обращаьтся к репозиторию (БД)

    public ClinicService(ClinicRepository repository) {
        this.repository = repository;
    }


    public List<Clinic> findAll() {
        return repository.findAll();
    }

    public Clinic add(Clinic clinic) {
        return repository.save(clinic); // метод save обновляет или создает новый объект, если его не было
    }

    public Clinic update(Clinic clinic){
        return repository.save(clinic); // метод save обновляет или создает новый объект, если его не было
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public List<Clinic> findByName(String text){
        return repository.findByName(text);
    }

    public Clinic findById(Long id){
        return repository.findById(id).get(); // т.к. возвращается Optional - нужно получить объект методом get()
    }

}

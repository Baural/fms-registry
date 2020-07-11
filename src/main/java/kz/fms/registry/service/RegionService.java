package kz.fms.registry.service;


import kz.fms.registry.entity.Region;
import kz.fms.registry.repository.RegionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author baur
 * @date on 08.07.2020
 */

@Service
@Transactional
public class RegionService {
    private final RegionRepository repository;

    public RegionService(RegionRepository repository) {
        this.repository = repository;
    }


    public List<Region> findAll() {
        return repository.findAll();
    }

    public Region add(Region region) {
        return repository.save(region);
    }

    public Region update(Region region){
        return repository.save(region);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }


    public Region findById(Long id){
        return repository.findById(id).get();
    }

    public List<Region> findByName(String name){
        return repository.findByName();
    }

}

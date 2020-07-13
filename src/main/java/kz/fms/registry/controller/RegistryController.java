package kz.fms.registry.controller;

import kz.fms.registry.entity.Clinic;
import kz.fms.registry.entity.Patient;
import kz.fms.registry.entity.Registry;
import kz.fms.registry.search.RegistrySearchValues;
import kz.fms.registry.service.RegistryService;
import kz.fms.registry.util.MyLogger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author baur
 * @date on 01.07.2020
 */

@RestController
@RequestMapping("/registry")
@CrossOrigin(origins = "http://localhost:4200")
public class RegistryController {

    private final RegistryService registryService;

    public RegistryController(RegistryService registryService) {
        this.registryService = registryService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Registry>> findAll() {

        return ResponseEntity.ok(registryService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Registry> add(@RequestBody Registry registry) {

        if (registry.getId() != null && registry.getId() != 0) {
            return new ResponseEntity("incorrect param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (registry.getPatient() == null) {
            return new ResponseEntity("missed param: patient", HttpStatus.NOT_ACCEPTABLE);
        }
        if (registry.getClinic() == null) {
            return new ResponseEntity("missed param: clinic", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(registryService.add(registry));
    }


    @PutMapping("/update")
    public ResponseEntity<Registry> update(@RequestBody Registry registry) {

        if (registry.getId() != null && registry.getId() != 0) {
            return new ResponseEntity("incorrect param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (registry.getPatient() == null) {
            return new ResponseEntity("missed param: patient", HttpStatus.NOT_ACCEPTABLE);
        }
        if (registry.getClinic() == null) {
            return new ResponseEntity("missed param: clinic", HttpStatus.NOT_ACCEPTABLE);
        }

        registryService.update(registry);

        return new ResponseEntity(HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        try {
            registryService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Registry> findById(@PathVariable Long id) {

        MyLogger.showMethodName("registry: findById() ---------------------------------------------------------------- ");

        Registry registry = null;

        try {
            registry = registryService.findById(id);
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(registry);
    }


    @PostMapping("/search")
    public ResponseEntity<Page<Registry>> search(@RequestBody RegistrySearchValues registrySearchValues) {

        Long patient = registrySearchValues.getPatient() != null ? registrySearchValues.getPatient() : null;

        Long clinic = registrySearchValues.getClinic() != null ? registrySearchValues.getClinic() : null;

        String sortColumn = registrySearchValues.getSortColumn() != null ? registrySearchValues.getSortColumn() : null;
        String sortDirection = registrySearchValues.getSortDirection() != null ? registrySearchValues.getSortDirection() : null;

        Integer pageNumber = registrySearchValues.getPageNumber() != null ? registrySearchValues.getPageNumber() : null;
        Integer pageSize = registrySearchValues.getPageSize() != null ? registrySearchValues.getPageSize() : null;


        Sort.Direction direction = sortDirection == null || sortDirection.trim().length() == 0 || sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;


        Sort sort = Sort.by(direction, sortColumn);


        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);


        Page result = registryService.findByParams(patient, clinic, pageRequest);

        return ResponseEntity.ok(result);
    }
}

package kz.fms.registry.controller;

import kz.fms.registry.entity.Registry;
import kz.fms.registry.service.RegistryService;
import kz.fms.registry.util.MyLogger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author baur
 * @date on 09.07.2020
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

        MyLogger.showMethodName("registry: findAll() ---------------------------------------------------------------- ");

        return ResponseEntity.ok(registryService.findAll());
    }


    @PostMapping("/add")
    public ResponseEntity<Registry> add(@RequestBody Registry registry) {

        MyLogger.showMethodName("registry: add() ---------------------------------------------------------------- ");


        if (registry.getId() != null && registry.getId() != 0) {

            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }


        if (registry.getTitle() == null || registry.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(registryService.add(registry));
    }


    @PutMapping("/update")
    public ResponseEntity<Registry> update(@RequestBody Registry registry) {

        MyLogger.showMethodName("registry: update() ---------------------------------------------------------------- ");


        if (registry.getId() == null || registry.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }


        if (registry.getTitle() == null || registry.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }


        registryService.update(registry);

        return new ResponseEntity(HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        MyLogger.showMethodName("registry: delete() ---------------------------------------------------------------- ");

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

        MyLogger.showMethodName("registry: search() ---------------------------------------------------------------- ");


        String title = registrySearchValues.getTitle() != null ? registrySearchValues.getTitle() : null;

        // конвертируем Boolean в Integer
        Integer completed = registrySearchValues.getCompleted() != null ? registrySearchValues.getCompleted() : null;

        Long priorityId = registrySearchValues.getPriorityId() != null ? registrySearchValues.getPriorityId() : null;
        Long categoryId = registrySearchValues.getCategoryId() != null ? registrySearchValues.getCategoryId() : null;

        String sortColumn = registrySearchValues.getSortColumn() != null ? registrySearchValues.getSortColumn() : null;
        String sortDirection = registrySearchValues.getSortDirection() != null ? registrySearchValues.getSortDirection() : null;

        Integer pageNumber = registrySearchValues.getPageNumber() != null ? registrySearchValues.getPageNumber() : null;
        Integer pageSize = registrySearchValues.getPageSize() != null ? registrySearchValues.getPageSize() : null;


        Sort.Direction direction = sortDirection == null || sortDirection.trim().length() == 0 || sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;


        Sort sort = Sort.by(direction, sortColumn);


        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);


        Page result = registryService.findByParams(title, completed, priorityId, categoryId, pageRequest);


        return ResponseEntity.ok(result);

    }

}

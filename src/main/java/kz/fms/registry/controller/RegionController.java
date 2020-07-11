package kz.fms.registry.controller;


import kz.fms.registry.entity.Region;
import kz.fms.registry.search.RegionSearchValues;
import kz.fms.registry.service.RegionService;
import kz.fms.registry.util.MyLogger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author baur
 * @date on 01.07.2020
 */

@RestController
@RequestMapping("/region")
@CrossOrigin(origins = "http://localhost:4200")
public class RegionController {

    private RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }


    @GetMapping("/all")
    public List<Region> findAll() {

        MyLogger.showMethodName("RegionController: findAll() ---------------------------------------------------------- ");
        return regionService.findAll();
    }


    @PostMapping("/add")
    public ResponseEntity<Region> add(@RequestBody Region region) {


        MyLogger.showMethodName("RegionController: add() ---------------------------------------------------------- ");


        if (region.getId() != null && region.getId() != 0) {
            return new ResponseEntity("incorrect param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (region.getName() == null || region.getName().trim().length() == 0) {
            return new ResponseEntity("missed param: region name", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(regionService.add(region));
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Region region) {

        if (region.getId() == null || region.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        if (region.getName() == null || region.getName().trim().length() == 0) {
            return new ResponseEntity("missed param: region name", HttpStatus.NOT_ACCEPTABLE);
        }

        regionService.update(region);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Region> findById(@PathVariable Long id) {

        Region region = null;

        try {
            region = regionService.findById(id);
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(region);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        MyLogger.showMethodName("RegionController: delete() ---------------------------------------------------------- ");

        try {
            regionService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Region>> search(@RequestBody RegionSearchValues regionSearchValues) {

        MyLogger.showMethodName("RegionController: search() ---------------------------------------------------------- ");

        return ResponseEntity.ok(regionService.findByName(regionSearchValues.getName()));
    }


}

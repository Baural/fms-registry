package kz.fms.registry.controller;

import kz.fms.registry.entity.Clinic;
import kz.fms.registry.search.ClinicSearchValues;
import kz.fms.registry.service.ClinicService;
import kz.fms.registry.util.MyLogger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author baur
 * @date on 08.07.2020
 */

@RestController
@RequestMapping("/clinic")
@CrossOrigin(origins = "http://localhost:4200")
public class ClinicController {

    private ClinicService clinicService;

    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }


    @GetMapping("/all")
    public List<Clinic> findAll() {

        return clinicService.findAll();
    }


    @PostMapping("/add")
    public ResponseEntity<Clinic> add(@RequestBody Clinic clinic) {


        MyLogger.showMethodName("ClinicController: add() ---------------------------------------------------------- ");


        if (clinic.getId() != null && clinic.getId() != 0) {

            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (clinic.getName() == null || clinic.getName().trim().length() == 0) {
            return new ResponseEntity("missed param: clinic name", HttpStatus.NOT_ACCEPTABLE);
        }

        if (clinic.getRegion() != null) {

            return new ResponseEntity("missed param: region id", HttpStatus.NOT_ACCEPTABLE);
        }



        return ResponseEntity.ok(clinicService.add(clinic));
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Clinic clinic) {

        if (clinic.getId() != null && clinic.getId() != 0) {

            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (clinic.getName() == null || clinic.getName().trim().length() == 0) {
            return new ResponseEntity("missed param: clinic name", HttpStatus.NOT_ACCEPTABLE);
        }

        if (clinic.getRegion() != null) {

            return new ResponseEntity("missed param: region id", HttpStatus.NOT_ACCEPTABLE);
        }


        clinicService.update(clinic);

        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Clinic> findById(@PathVariable Long id) {


        Clinic clinic = null;

        try {
            clinic = clinicService.findById(id);
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(clinic);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        MyLogger.showMethodName("ClinicController: delete() ---------------------------------------------------------- ");

        try {
            clinicService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Clinic>> search(@RequestBody ClinicSearchValues clinicSearchValues) {

        MyLogger.showMethodName("ClinicController: search() ---------------------------------------------------------- ");


        return ResponseEntity.ok(clinicService.findByName(clinicSearchValues.getName()));
    }


}

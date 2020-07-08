package kz.fms.registry.controller;


import kz.fms.registry.entity.Patient;
import kz.fms.registry.search.PatientSearchValues;
import kz.fms.registry.service.PatientService;
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
@RequestMapping("/patient")
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @GetMapping("/all")
    public List<Patient> findAll() {

        MyLogger.showMethodName("PatientController: findAll() ---------------------------------------------------------- ");
        return patientService.findAllByOrderByIinAsc();
    }


    @PostMapping("/add")
    public ResponseEntity<Patient> add(@RequestBody Patient patient) {


        MyLogger.showMethodName("PatientController: add() ---------------------------------------------------------- ");


        if (patient.getId() != null && patient.getId() != 0) {

            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (patient.getIin() == null || patient.getIin().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }


        return ResponseEntity.ok(patientService.add(patient));
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Patient patient) {

        MyLogger.showMethodName("PatientController: update() ---------------------------------------------------------- ");


        if (patient.getId() == null || patient.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        if (patient.getIin() == null || patient.getIin().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        patientService.update(patient);

        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Patient> findById(@PathVariable Long id) {

        MyLogger.showMethodName("PatientController: findById() ---------------------------------------------------------- ");


        Patient patient = null;


        try {
            patient = patientService.findById(id);
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(patient);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        MyLogger.showMethodName("PatientController: delete() ---------------------------------------------------------- ");

        try {
            patientService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Patient>> search(@RequestBody PatientSearchValues patientSearchValues) {

        MyLogger.showMethodName("PatientController: search() ---------------------------------------------------------- ");


        return ResponseEntity.ok(patientService.findByIin(patientSearchValues.getIin()));
    }


}

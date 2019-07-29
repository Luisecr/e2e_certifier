package com.e2e.certifier.Controllers;

import com.e2e.certifier.Entities.Dictamen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.e2e.certifier.repositories.DictamenRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class DictamenController {

    @Autowired
    DictamenRepository repository;

    @GetMapping("/dictamenes")
    public List<Dictamen> getAllDictamenes() {
        System.out.println("Get all Dictamens...");

        List<Dictamen> Dictamenes = new ArrayList<>();
        repository.findAll().forEach(Dictamenes::add);

        return Dictamenes;
    }

    @PostMapping("/dictamen")
    public Dictamen postDictamen(@RequestBody Dictamen dictamen) {

        Dictamen _Dictamen = repository.save(new Dictamen(dictamen.getId(), dictamen.getFolio()));
        return _Dictamen;
    }

    @DeleteMapping("/dictamen/{id}")
    public ResponseEntity<String> deleteDictamen(@PathVariable("id") long id) {
        System.out.println("Delete Dictamen with ID = " + id + "...");

        repository.deleteById(id);

        return new ResponseEntity<>("Dictamen has been deleted!", HttpStatus.OK);
    }

    @GetMapping("dictamenes/folio/{folio}")
    public List<Dictamen> findByFolio(@PathVariable String folio) {

        List<Dictamen> Dictamenes = repository.findByFolio(folio);
        return Dictamenes;
    }

    @PutMapping("/dictamen/{id}")
    public ResponseEntity<Dictamen> updateDictamen(@PathVariable("id") long id, @RequestBody Dictamen dictamen) {
        System.out.println("Update Dictamen with ID = " + id + "...");

        Optional<Dictamen> dictamenData = repository.findById(id);

        if (dictamenData.isPresent()) {
            Dictamen _Dictamen = dictamenData.get();
            _Dictamen.setFolio(dictamen.getFolio());
            return new ResponseEntity<>(repository.save(_Dictamen), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


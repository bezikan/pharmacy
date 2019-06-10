package com.example.pharmacy.controller;

import com.example.pharmacy.exception.DrugNotFoundException;
import com.example.pharmacy.model.Drug;
import com.example.pharmacy.repository.DrugRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DrugController {

    @Autowired
    public DrugRepository drugRepository;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/drugs/{id}")
    public ResponseEntity<?> getDrug(@PathVariable Long id) {
        return drugRepository.findById(id).map(drug -> {
             return new ResponseEntity<>(drug, HttpStatus.OK);
        }).orElseThrow(()->new DrugNotFoundException(id));
    }

    @GetMapping("/drugs")
    public ResponseEntity<?> getAllDrugs() {
        List<Drug> drugList = drugRepository.findAll();
        if(drugList.isEmpty()){
            logger.info("empty");
        }
        return new ResponseEntity<>(drugList, HttpStatus.ACCEPTED);
    }

    @PostMapping("/drug")
    public ResponseEntity<?> createDrug(@RequestBody Drug drug) {
        if(drugRepository.findById(drug.getId()).isPresent()) {
            logger.info("exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/drugs/{id}")
    public  ResponseEntity<?> editDrug(@PathVariable Long id, @RequestBody Drug drug) {
        return drugRepository.findById(id).map(theDrug-> {
            theDrug.setId(drug.getId());
            theDrug.setNameDrug(drug.getNameDrug());
            theDrug.setDescription(drug.getDescription());
            return new ResponseEntity<>(theDrug, HttpStatus.OK);
        }).orElseThrow(()->new DrugNotFoundException(id));
    }

    @DeleteMapping("/drugs/{id}")
    public ResponseEntity<?> deleteDrug(@PathVariable Long id) {
        drugRepository.findById(id).orElseThrow(()->new DrugNotFoundException(id));
        drugRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

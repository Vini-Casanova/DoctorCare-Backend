package com.example.heathCare;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/medics")
public class MedicController {
  
  @Autowired
  private MedicRepository repository;
  
  @PostMapping("/addMedic")
  public String savePacient(@RequestBody Medic medic) {
      if(medic.getId() != null) {
        if(repository.findById(medic.getId())!= null) {
          repository.save(medic);
          return "Medic Edited";
        }
      }
      String medicId = ""+(repository.count()+1);
      medic.setId(medicId);
      repository.save(medic);
      return "Medic Save with id: "+medic.getId();
  }
  
  @GetMapping("/findAllMedics")
  public List<Medic> getPacients(){
      return repository.findAll();
  }
  
  @GetMapping("/findAllMedic/{id}")
  public Optional<Medic> getPacient(@PathVariable String id){
      return repository.findById(id);
  }
  
  @DeleteMapping("/deleteMedic/{id}")
  public String deletePacient(@PathVariable String id) {
      repository.deleteById(id);
      return "Medic with id: "+ id +  "has been deleted";
  }

}
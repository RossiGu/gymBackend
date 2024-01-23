package com.rossi.gym.resource;

import com.rossi.gym.entity.Gym;
import com.rossi.gym.service.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/gym")
public class GymResource {

    @Autowired
    private GymService gymService;

    @GetMapping
    public ResponseEntity<List<Gym>> findAll() {
        List<Gym> gymList = gymService.findAll();
        return ResponseEntity.ok().body(gymList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Gym> findById(@PathVariable UUID id) {
        Gym gymObj = gymService.findById(id);
        return ResponseEntity.ok().body(gymObj);
    }

    @PostMapping
    public ResponseEntity<Gym> insert(@RequestBody Gym gymObj) {
        gymObj = gymService.insert(gymObj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(gymObj.getId()).toUri();
        return ResponseEntity.created(uri).body(gymObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        gymService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Gym> update(@PathVariable UUID id, @RequestBody Gym gymObj) {
        gymObj = gymService.update(id, gymObj);
        return ResponseEntity.ok().body(gymObj);
    }
}

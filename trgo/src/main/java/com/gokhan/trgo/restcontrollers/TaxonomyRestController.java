package com.gokhan.trgo.restcontrollers;

import com.gokhan.trgo.entities.Taxonomy;
import com.gokhan.trgo.services.TaxonomyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/taxonomy")
public class TaxonomyRestController {

    final TaxonomyService tService;

    public TaxonomyRestController(TaxonomyService tService) {
        this.tService = tService;
    }


    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Taxonomy taxonomy) {
        return tService.add(taxonomy);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam Integer id) {
        return tService.delete(id);
    }

    @GetMapping("/list")
    public ResponseEntity list() {
        return tService.list();
    }

    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody Taxonomy taxonomy){
        return tService.update(taxonomy);
    }
}


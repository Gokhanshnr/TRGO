package com.gokhan.trgo.restcontrollers;


import com.gokhan.trgo.entities.Pictures;
import com.gokhan.trgo.services.PicturesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
@CrossOrigin
public class PicturesRestController {

    final PicturesService pService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody Pictures pictures) {
        return pService.add(pictures);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        return pService.delete(id);
    }

}

package com.gokhan.trgo.restcontrollers;

import com.gokhan.trgo.entities.Rooms;
import com.gokhan.trgo.services.RoomsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/room")
public class RoomsRestController {

    final RoomsService roomsService;

    public RoomsRestController(RoomsService roomsService) {
        this.roomsService = roomsService;
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Rooms rooms) {
        return roomsService.add(rooms);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam Integer id) {
        return roomsService.delete(id);
    }

    @GetMapping("/list")
    public ResponseEntity list() {
        return roomsService.list();
    }
}

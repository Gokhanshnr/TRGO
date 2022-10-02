package com.gokhan.trgo.services;

import com.gokhan.trgo.entities.Rooms;
import com.gokhan.trgo.repositories.RoomsRepository;
import com.gokhan.trgo.utils.REnum;
import com.gokhan.trgo.utils.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RoomsService {

    final RoomsRepository roomsRepository;

    public RoomsService(RoomsRepository roomsRepository) {
        this.roomsRepository = roomsRepository;
    }

    public ResponseEntity add(Rooms room) {
        Map<REnum, Object> hm = new LinkedHashMap();
            String capitalizedName = Util.capitalizedWords(room.getName());
            room.setName(capitalizedName);
            Rooms sRoom = roomsRepository.save(room);
            roomsRepository.setProductId(sRoom.getRoom_id(), room.getProduct_id());
            hm.put(REnum.status, true);
            hm.put(REnum.result, sRoom);
            return new ResponseEntity( hm , HttpStatus.OK);
    }

    public ResponseEntity delete(Integer id) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            roomsRepository.deleteById(id);
            hm.put(REnum.status, true);
            return new ResponseEntity<>(hm, HttpStatus.OK);
        } catch (Exception ex) {
            hm.put(REnum.status, false);
            hm.put(REnum.error, ex.getMessage());
            return new ResponseEntity<>(hm, HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity list() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<Rooms> roomsList = roomsRepository.findAll();
        hm.put(REnum.status, true);
        hm.put(REnum.result, roomsList);
        return new ResponseEntity<>(hm, HttpStatus.OK);
    }


}

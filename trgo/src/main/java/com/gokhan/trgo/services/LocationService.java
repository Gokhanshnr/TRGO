package com.gokhan.trgo.services;

import com.gokhan.trgo.entities.Location;
import com.gokhan.trgo.entities.Role;
import com.gokhan.trgo.repositories.LocationRepository;
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
public class LocationService {
    final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public ResponseEntity add(Location location) {
        Map<REnum, Object> hm = new LinkedHashMap();
        Optional<Location> optionalLocation = locationRepository
                .findByCityEqualsIgnoreCaseAndDistrictEqualsIgnoreCase(location.getCity(), location.getDistrict());
        if (!optionalLocation.isPresent()) {
            String capitalizedCity = Util.capitalizedWords(location.getCity());
            String capitalizedDistrict = Util.capitalizedWords(location.getDistrict());
            location.setCity(capitalizedCity);
            location.setDistrict(capitalizedDistrict);
            Location sLocation = locationRepository.save(location);
            hm.put(REnum.status, true);
            hm.put(REnum.result, sLocation);
            return new ResponseEntity( hm , HttpStatus.OK);
        } else {
            hm.put(REnum.status, false);
            hm.put(REnum.message, "There is already a location with this name");
            return new ResponseEntity<>(hm, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public ResponseEntity delete(Integer id) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            locationRepository.deleteById(id);
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
        List<Location> locationList = locationRepository.findAll();
        hm.put(REnum.status, true);
        hm.put(REnum.result, locationList);
        return new ResponseEntity<>(hm, HttpStatus.OK);
    }


    public ResponseEntity update(Location location) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            Optional<Location> oldLocation = locationRepository.findById(location.getLid());
            Optional<Location> optionalLocation = locationRepository
                    .findByCityEqualsIgnoreCaseAndDistrictEqualsIgnoreCase(location.getCity(), location.getDistrict());
            if (oldLocation.isPresent() && !optionalLocation.isPresent()) {
                String capitalizedCity = Util.capitalizedWords(location.getCity());
                String capitalizedDistrict = Util.capitalizedWords(location.getDistrict());
                location.setCity(capitalizedCity);
                location.setDistrict(capitalizedDistrict);
                locationRepository.saveAndFlush(location);
                hm.put(REnum.status, true);
                hm.put(REnum.message, "Update is successful");
                hm.put(REnum.result, location);
                return new ResponseEntity<>(hm, HttpStatus.OK);
            }else {
                hm.put(REnum.status, false);
                hm.put(REnum.message, "There is no record with this id or there is a record with a new record");
                return new ResponseEntity<>(hm, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex) {
            hm.put(REnum.status, false);
            hm.put(REnum.message, ex.getMessage());
        }
        return new ResponseEntity<>(hm, HttpStatus.BAD_REQUEST);
    }

}

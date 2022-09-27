package com.gokhan.trgo.services;

import com.gokhan.trgo.entities.Location;
import com.gokhan.trgo.entities.Taxonomy;
import com.gokhan.trgo.repositories.TaxonomyRepository;
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
public class TaxonomyService {

    final TaxonomyRepository tRepository;

    public TaxonomyService(TaxonomyRepository tRepository) {
        this.tRepository = tRepository;
    }


    public ResponseEntity add(Taxonomy taxonomy) {
        Map<REnum, Object> hm = new LinkedHashMap();
        Optional<Taxonomy> optionalTaxonomy = tRepository
                .findByNameEqualsIgnoreCase(taxonomy.getName());
        if (!optionalTaxonomy.isPresent()) {
            String capitalizedName = Util.capitalizedWords(taxonomy.getName());
            taxonomy.setName(capitalizedName);
            Taxonomy sTaxonomy = tRepository.save(taxonomy);
            hm.put(REnum.status, true);
            hm.put(REnum.result, sTaxonomy);
            return new ResponseEntity( hm , HttpStatus.OK);
        } else {
            hm.put(REnum.status, false);
            hm.put(REnum.message, "There is already a taxonomy with this name");
            return new ResponseEntity<>(hm, HttpStatus.NOT_ACCEPTABLE);
        }
    }


    public ResponseEntity delete(Integer id) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            tRepository.deleteById(id);
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
        List<Taxonomy> taxonomyList = tRepository.findAll();
        hm.put(REnum.status, true);
        hm.put(REnum.result, taxonomyList);
        return new ResponseEntity<>(hm, HttpStatus.OK);
    }


    public ResponseEntity update(Taxonomy taxonomy) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            Optional<Taxonomy> oldTaxonomy = tRepository.findById(taxonomy.getTax_id());
            Optional<Taxonomy> optionalTaxonomy = tRepository
                    .findByNameEqualsIgnoreCase(taxonomy.getName());
            if (oldTaxonomy.isPresent() && !optionalTaxonomy.isPresent()) {
                String capitalizedName = Util.capitalizedWords(taxonomy.getName());
                taxonomy.setName(capitalizedName);
                tRepository.saveAndFlush(taxonomy);
                hm.put(REnum.status, true);
                hm.put(REnum.message, "Update is successful");
                hm.put(REnum.result, taxonomy);
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

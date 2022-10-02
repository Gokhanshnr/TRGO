package com.gokhan.trgo.services;


import com.gokhan.trgo.entities.Comment;
import com.gokhan.trgo.props.JoinUserComment;
import com.gokhan.trgo.repositories.CommentRepository;
import com.gokhan.trgo.repositories.JoinUserCommentRepository;
import com.gokhan.trgo.repositories.ProductRepository;
import com.gokhan.trgo.utils.REnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service

public class CommentService {

    final CommentRepository cRepository;
    final ProductRepository pRepository;

    final JoinUserCommentRepository jRepository;

    public CommentService(CommentRepository cRepository, ProductRepository pRepository, JoinUserCommentRepository jRepository) {
        this.cRepository = cRepository;
        this.pRepository = pRepository;
        this.jRepository = jRepository;
    }


    public ResponseEntity add(Comment comment) {
        Map<REnum, Object> hm = new LinkedHashMap();
        Boolean isProId = pRepository.existsByPidEquals(comment.getProid());
        if(isProId) {
            Comment sComment = cRepository.save(comment);
            hm.put(REnum.status, true);
            hm.put(REnum.result, sComment);
            return new ResponseEntity(hm, HttpStatus.OK);
        }else{
            hm.put(REnum.status, false);
            hm.put(REnum.message, "Product id is wrong");
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity delete(Integer id) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            cRepository.deleteById(id);
            hm.put(REnum.status, true);
            return new ResponseEntity<>(hm, HttpStatus.OK);
        } catch (Exception ex) {
            hm.put(REnum.status, false);
            hm.put(REnum.error, ex.getMessage());
            return new ResponseEntity<>(hm, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity listByPid(Integer id) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<JoinUserComment> commentList = jRepository.listCommentByPid(id);
        hm.put(REnum.status, true);
        hm.put(REnum.result, commentList);
        return new ResponseEntity<>(hm, HttpStatus.OK);
    }

    public ResponseEntity listByUid(Integer id) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<JoinUserComment> commentList = jRepository.listCommentByUid(id);
        hm.put(REnum.status, true);
        hm.put(REnum.result, commentList);
        return new ResponseEntity<>(hm, HttpStatus.OK);
    }

}
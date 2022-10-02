package com.gokhan.trgo.repositories;

import com.gokhan.trgo.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByProidEquals(Integer proid);

    List<Comment> findByUser_IdEquals(Long id);

}
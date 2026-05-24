package com.heartzclub.heartzclub.Repository;

import com.heartzclub.heartzclub.Model.ComentarioJogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioJogoRepository extends JpaRepository<ComentarioJogo, Long> {

    List<ComentarioJogo> findByJogoIdOrderByDataComentarioDesc(Long jogoId);
}
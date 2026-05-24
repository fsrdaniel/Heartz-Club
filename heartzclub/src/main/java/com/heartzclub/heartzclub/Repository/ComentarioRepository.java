package com.heartzclub.heartzclub.Repository;

import com.heartzclub.heartzclub.Model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository
        extends JpaRepository<Comentario, Long> {
}
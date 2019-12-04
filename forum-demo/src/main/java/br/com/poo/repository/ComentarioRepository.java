package br.com.poo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poo.model.Comentario;



public interface ComentarioRepository extends JpaRepository<Comentario, Long> {}

package br.com.poo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poo.model.Whisky;

public interface WhiskyRepository extends JpaRepository<Whisky, Long> {

}

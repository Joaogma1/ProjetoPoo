package br.com.poo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poo.model.Vodka;

public interface VodkaRepository extends JpaRepository<Vodka, Long> {

}

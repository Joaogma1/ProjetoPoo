package br.com.poo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.poo.model.Vinho;

@Repository

public interface VinhoRepository extends JpaRepository<Vinho, Long>{}

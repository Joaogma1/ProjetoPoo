package br.com.poo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poo.model.Licor;

public interface LicorRepository extends JpaRepository<Licor, Long> {

}

package br.com.poo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.poo.model.Bebida;
import br.com.poo.repository.BebidaRepository;
import javassist.NotFoundException;

@RestController
@RequestMapping("/api")
public class BebidasController {

	@Autowired
	private BebidaRepository _bebidaRepository;

	@GetMapping("/bebidas")
	public List<Bebida> get() {
		return _bebidaRepository.findAll();
	}
	@GetMapping("/bebidas/{id}")
	public Bebida get(@PathVariable long id) throws NotFoundException {
		Optional<Bebida> bebidaBuscada = _bebidaRepository.findById(id);
		
		if (!bebidaBuscada.isPresent())
			throw new NotFoundException("Não foi possivel encontrar a bebida");

		return bebidaBuscada.get();
	}	
}

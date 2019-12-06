package br.com.poo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.poo.model.Vinho;
import br.com.poo.repository.VinhoRepository;



@RestController
@RequestMapping("/api")
public class VinhoController {

	@Autowired
	private VinhoRepository vinhoRepository;
	
	@CrossOrigin()
	@GetMapping("/vinhos")
	public List<Vinho> get() {
		return vinhoRepository.findAll();
	}
	@CrossOrigin()
	@PutMapping("/vinhos/{id}")
	public ResponseEntity<Object> update(@RequestBody Vinho vinho, @PathVariable long id) {

		Optional<Vinho> vin =vinhoRepository.findById(id);

		if (!vin.isPresent())
			return ResponseEntity.notFound().build();
		else {
		Vinho vinhoModificado = vin.get();
		
		vinhoModificado.setId(id);
		vinhoRepository.save(vinhoModificado);

		return ResponseEntity.noContent().build();
		}
	}
	@CrossOrigin()
	@PostMapping("/vinhos")
	public ResponseEntity<Object> cadastrarVinho(@RequestBody Vinho data) {
		
		Vinho savedVinho = vinhoRepository.save(data);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedVinho.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
}

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
import br.com.poo.model.Vodka;
import br.com.poo.repository.VodkaRepository;

@RestController
@RequestMapping("/api")
public class VodkaController {

	@Autowired
	private VodkaRepository vodkaRepository;
	@CrossOrigin()
	@GetMapping("/Vodkas")
	public List<Vodka> get() {
		return vodkaRepository.findAll();
	}
	@CrossOrigin()
	@PutMapping("/Vodkas/{id}")
	public ResponseEntity<Object> update(@RequestBody Vodka vodka, @PathVariable long id) {

		Optional<Vodka> vodkaOptional = vodkaRepository.findById(id);

		if (!vodkaOptional.isPresent())
			return ResponseEntity.notFound().build();

		vodka.setId(id);
		
		vodkaRepository.save(vodka);

		return ResponseEntity.noContent().build();
	}
	@CrossOrigin()
	@PostMapping("/Vodkas")
	public ResponseEntity<Object> cadastrarVodka(@RequestBody Vodka data) {
		
		Vodka savedVodka = vodkaRepository.save(data);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedVodka.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
}

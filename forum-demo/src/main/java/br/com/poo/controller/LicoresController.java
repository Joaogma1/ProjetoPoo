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

import br.com.poo.model.Licor;
import br.com.poo.repository.LicorRepository;


@RestController
@RequestMapping("/api")
public class LicoresController {
	
	@Autowired
	private LicorRepository licorRepository;
	
	@CrossOrigin()
	@GetMapping("/licores")
	public List<Licor> get() {
		return licorRepository.findAll();
	}
	
	@CrossOrigin()
	@PutMapping("/licores/{id}")
	public ResponseEntity<Object> update(@RequestBody Licor licor, @PathVariable long id) {

		Optional<Licor> studentOptional = licorRepository.findById(id);

		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();

		licor.setId(id);
		
		licorRepository.save(licor);

		return ResponseEntity.noContent().build();
	}
	@CrossOrigin()
	@PostMapping("/licores")
	public ResponseEntity<Object> cadastrarLicor(@RequestBody Licor data) {
		
		Licor savedLicor = licorRepository.save(data);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedLicor.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

}

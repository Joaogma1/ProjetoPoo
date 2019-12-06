package br.com.poo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.poo.model.Comentario;
import br.com.poo.repository.ComentarioRepository;
import br.com.poo.repository.PostRepository;
import javassist.NotFoundException;

@RestController
@RequestMapping("/api")
public class ComentariosController {
	@Autowired
	private ComentarioRepository comentarioRepository;
	@Autowired
	private PostRepository postRepository;
	@GetMapping("/comentarios")
	public List<Comentario> get() {
		return comentarioRepository.findAll();
	}
	
	@GetMapping("/comentarios/{id}")
	public Comentario get(@PathVariable long id) throws NotFoundException {
		Optional<Comentario> comentarioBuscado = comentarioRepository.findById(id);
		
		if (!comentarioBuscado.isPresent())
			throw new NotFoundException("Não foi possivel encontrar a bebida");

		return comentarioBuscado.get();
	}
	
	@DeleteMapping("/comentarios/{id}")
	public void deleteStudent(@PathVariable long id) {
		comentarioRepository.deleteById(id);
	}
	
	@PutMapping("/comentarios/{id}")
	public ResponseEntity<Object> update(@RequestBody Comentario comentario, @PathVariable long id) {

		Optional<Comentario> studentOptional = comentarioRepository.findById(id);

		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();

		comentario.setId(id);
		
		comentarioRepository.save(comentario);

		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/comentarios")
	public ResponseEntity<Object> cadastrarPost(@RequestBody Comentario data) {
		
		data.setPost(postRepository.getOne(data.getIdPost()));
		
		Comentario savedComentario = comentarioRepository.save(data);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedComentario.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
}

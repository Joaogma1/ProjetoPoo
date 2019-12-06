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

import br.com.poo.model.Bebida;
import br.com.poo.model.Post;
import br.com.poo.repository.BebidaRepository;
import br.com.poo.repository.PostRepository;
import javassist.NotFoundException;
import javassist.expr.Instanceof;

@RestController
@RequestMapping("/api")
public class PostsController {

	@Autowired
	private PostRepository postRepository;
	@Autowired
	private BebidaRepository bebidaRepository;
	
	@GetMapping("/posts")
	public List<Post> get() {
		return postRepository.findAll();
	}
	
	@GetMapping("/posts/{id}")
	public Post get(@PathVariable long id) throws NotFoundException {
		Optional<Post> postBuscado = postRepository.findById(id);
		
		if (!postBuscado.isPresent())
			throw new NotFoundException("Não foi possivel encontrar a bebida");

		return postBuscado.get();
	}
	
	@DeleteMapping("/posts/{id}")
	public void deleteStudent(@PathVariable long id) {
		postRepository.deleteById(id);
	}
	
	@PutMapping("/posts/{id}")
	public ResponseEntity<Object> update(@RequestBody Post post, @PathVariable long id) {

		Optional<Post> postOptional = postRepository.findById(id);

		if (!postOptional.isPresent())
			return ResponseEntity.notFound().build();

		post.setId(id);
		
		postRepository.save(post);

		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/posts")
	public ResponseEntity<Object> cadastrarPost(@RequestBody Post data) {

		Optional<Bebida> bebidaBuscada = bebidaRepository.findById(data.getIdBebida());
		
		data.setBebida(bebidaBuscada.get());
		
		Post savedPost = postRepository.save(data);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedPost.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
}
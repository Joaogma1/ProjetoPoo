package br.com.poo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poo.model.Post;


public interface PostRepository extends JpaRepository<Post, Long> {

}

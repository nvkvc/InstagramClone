package org.plu.RESTSpringBoot.repository;

import org.plu.RESTSpringBoot.model.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

}

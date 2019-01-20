package org.plu.RESTSpringBoot.rest.services;

import org.plu.RESTSpringBoot.model.dto.PostDto;
import org.plu.RESTSpringBoot.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public boolean insert(PostDto postDto) {
        return true;
    }
}

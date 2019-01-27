package org.plu.RESTSpringBoot.rest.controllers;

import org.plu.RESTSpringBoot.mappers.CommentDtoToCommentMapper;
import org.plu.RESTSpringBoot.model.dto.CommentDto;
import org.plu.RESTSpringBoot.model.dto.PostDto;
import org.plu.RESTSpringBoot.model.entities.ShareLikeDto;
import org.plu.RESTSpringBoot.rest.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping(path = "/insert", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean postImage(@RequestParam("postImage") MultipartFile file,
                                         @RequestParam("username") String username)
            throws IOException{
        return postService.postImage(file, username);
    }

    @PostMapping(path = "/share")
    public boolean sharePost(@RequestBody ShareLikeDto shareLikeDto) {
        return postService.sharePost(shareLikeDto);
    }

    @PostMapping(path = "/like")
    public boolean likePost(@RequestBody ShareLikeDto shareLikeDto) {
        return postService.likePost(shareLikeDto);
    }

    @PostMapping(path = "/comment")
    public boolean postComment(@RequestBody CommentDto commentDto) {
        return postService.postComment(commentDto);
    }
}

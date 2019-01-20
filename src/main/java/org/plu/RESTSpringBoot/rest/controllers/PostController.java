package org.plu.RESTSpringBoot.rest.controllers;

import org.plu.RESTSpringBoot.model.dto.PostDto;
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

    @PostMapping(path = "/insert", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> insert(@RequestParam("postImage") MultipartFile file,
                                         @RequestParam("postDescription") Integer authorId)
            throws IOException{
        File saveFile = new File("E:/Program Files/"+file.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        System.out.println(authorId);
        return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
    }

    @GetMapping(path = "/testic")
    public String testic() {
        return "testic";
    }
}

package org.plu.RESTSpringBoot.rest.services;

import org.plu.RESTSpringBoot.mappers.CommentDtoToCommentMapper;
import org.plu.RESTSpringBoot.model.dto.CommentDto;
import org.plu.RESTSpringBoot.model.dto.PostDto;
import org.plu.RESTSpringBoot.model.entities.ApplicationUser;
import org.plu.RESTSpringBoot.model.entities.Comment;
import org.plu.RESTSpringBoot.model.entities.Post;
import org.plu.RESTSpringBoot.model.entities.ShareLikeDto;
import org.plu.RESTSpringBoot.repository.ApplicationUserRepository;
import org.plu.RESTSpringBoot.repository.CommentRepository;
import org.plu.RESTSpringBoot.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class PostService {

    private PostRepository postRepository;
    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private CommentRepository commentRepository;

    @Autowired
    public PostService(ApplicationUserRepository applicationUserRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       PostRepository postRepository,
                       CommentRepository commentRepository) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public boolean postImage(MultipartFile image, String username) throws IOException {
        if(image == null || username == null) {
            return false;
        }

        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);

        if(applicationUser == null) {
            return false;
        }
        new File("E:/Program Files/posts/"+username).mkdirs();
        File saveFile = new File("E:/Program Files/posts/"+username+"/"+image.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
        fileOutputStream.write(image.getBytes());
        fileOutputStream.close();

        Post newPost = new Post();
        newPost.setAuthor(applicationUser);
        newPost.setOriginalFileName(image.getOriginalFilename());
        postRepository.save(newPost);
        return true;
    }

    public boolean postComment(CommentDto commentDto){
        if(commentDto == null) {
            return false;
        }

        Post post = postRepository.findById(commentDto.getPostId()).get();

        if(post == null) {
            return false;
        }

        Comment comment = new CommentDtoToCommentMapper().map(commentDto);
        comment.setPost(post);
        System.out.println(commentDto.getComment()+" "+comment.getComment());
        commentRepository.save(comment);
        return true;
    }

    public boolean sharePost(ShareLikeDto shareLikeDto){
        if(shareLikeDto == null) {
            System.out.println("Dto je null");
            return false;
        }
        System.out.println(shareLikeDto.getPostId()+" "+shareLikeDto.getUserId());
        ApplicationUser applicationUser = applicationUserRepository.findById(shareLikeDto.getUserId()).get();

        if(applicationUser == null) {
            System.out.println("applicationUser je null");
            return false;
        }

        Post post = postRepository.findById(shareLikeDto.getPostId()).get();

        if(post == null) {
            System.out.println("post je null");
            return false;
        }

        applicationUser.getSharedPosts().add(post);
        applicationUserRepository.save(applicationUser);

        Post post2 = postRepository.findById(shareLikeDto.getPostId()).get();
        System.out.println(post2.getSharedByUsers().size());
        return true;
    }

    public boolean likePost(ShareLikeDto shareLikeDto){
        if(shareLikeDto == null) {
            System.out.println("Dto je null");
            return false;
        }
        System.out.println(shareLikeDto.getPostId()+" "+shareLikeDto.getUserId());
        ApplicationUser applicationUser = applicationUserRepository.findById(shareLikeDto.getUserId()).get();

        if(applicationUser == null) {
            System.out.println("applicationUser je null");
            return false;
        }

        Post post = postRepository.findById(shareLikeDto.getPostId()).get();

        if(post == null) {
            System.out.println("post je null");
            return false;
        }

        applicationUser.getLikedPosts().add(post);
        applicationUserRepository.save(applicationUser);

        Post post2 = postRepository.findById(shareLikeDto.getPostId()).get();
        System.out.println(post2.getLikedByUsers().size());
        return true;
    }
}

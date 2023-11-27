package com.crio.starter.controller;

import lombok.RequiredArgsConstructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import com.crio.starter.exchange.PostDto;
import com.crio.starter.exchange.PostResponse;
import com.crio.starter.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memes")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostController {

    private final PostService postService;
    
    @PostMapping("/")
    ResponseEntity<Map<String,String>> createPost(@Valid @RequestBody PostDto postDto){    
        Map<String,String> map=new HashMap<>();
        PostDto postCreatedDto = postService.createPost(postDto);
        map.put("id", Long.toString(postCreatedDto.getId()));
        return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
    }

    //get atmost 100 latest post
    @GetMapping("/")
    ResponseEntity<List<PostResponse>> getPost(){
        List<PostResponse> responsePosts=postService.getPost();
        return new ResponseEntity<List<PostResponse>>(responsePosts, HttpStatus.OK);
    }

    //get post by id
    //if id not present return 404 error code
    @GetMapping("/{Id}")
    ResponseEntity<PostDto> getPostById(@PathVariable("Id") Long Id){
        PostDto responsePosts=postService.getPostById(Id);
        return new ResponseEntity<PostDto>(responsePosts, HttpStatus.OK);
    }
}

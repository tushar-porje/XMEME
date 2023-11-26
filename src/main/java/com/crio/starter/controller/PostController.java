package com.crio.starter.controller;

import lombok.RequiredArgsConstructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crio.starter.exchange.PostDto;
import com.crio.starter.exchange.PostResponse;
import com.crio.starter.repository.PostRepo;
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
    ResponseEntity<Map<String,String>> createPost(@RequestBody PostDto postDto){
        PostDto postCreatedDto;        
        Map<String,String> map=new HashMap<>();
        postCreatedDto = postService.createPost(postDto);
        map.put("id", Long.toString(postCreatedDto.getId()));
        return ResponseEntity.ok(map);
    }

    //get all atmost 100 latest post
    @GetMapping("/")
    ResponseEntity<List<PostResponse>> getPost(){
        List<PostResponse> responsePosts=postService.getPost();
        return ResponseEntity.ok(responsePosts);
    }

    @GetMapping("/{Id}")
    ResponseEntity<PostDto> getPostById(@PathVariable("Id") Long Id){
        PostDto responsePosts=postService.getPostById(Id);
        return ResponseEntity.ok(responsePosts);
    }
}

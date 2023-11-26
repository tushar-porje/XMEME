package com.crio.starter.service;

import java.util.List;
import com.crio.starter.exchange.PostDto;
import com.crio.starter.exchange.PostResponse;

public interface PostService{
    
    PostDto createPost(PostDto postDto);
    List<PostResponse> getPost();
    PostDto getPostById(Long id);
}

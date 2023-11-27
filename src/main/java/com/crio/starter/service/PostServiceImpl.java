package com.crio.starter.service;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.starter.Exception.DuplicatePostException;
import com.crio.starter.data.Post;
import com.crio.starter.exchange.PostDto;
import com.crio.starter.exchange.PostResponse;
import com.crio.starter.repository.PostRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostServiceImpl implements PostService{
    
    
    private final SequenceGeneratorService sequenceGeneratorService;

    private final ModelMapper mapper;

    private final PostRepo postRepo;


    public PostDto createPost(PostDto requestPostDto){

        Post post=mapper.map(requestPostDto, Post.class);

        // Post alreadyPresenPost=postRepo.findByName(post.getName());

        // if(alreadyPresenPost!=null && equals(alreadyPresenPost,post)){
        //     throw new Exception();
        // }

        boolean isPresent=postRepo.findByNameAndUrlAndCaption(post.getName(),post.getUrl(),post.getCaption()).isPresent();
        
        if(isPresent){
            throw new DuplicatePostException();
        }

        post.setId(sequenceGeneratorService.generateSequence(Post.SEQUENCE_NAME));
        Post savedPost=postRepo.save(post);
        PostDto responsePostDto=mapper.map(savedPost,PostDto.class);
        return responsePostDto;
        
    }

    public List<PostResponse> getPost(){
        Direction direction=Direction.DESC;
        
        Pageable p=PageRequest.of(0, 100, direction,"id");
        Page<Post> postPage=this.postRepo.findAll(p);
        List<Post> allPostsInPage=postPage.getContent();
        List<PostResponse> allPostDtos=allPostsInPage.stream().map(post->this.mapper.map(post,PostResponse.class)).collect(Collectors.toList());
        return allPostDtos;
    
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post=postRepo.findById(id).orElseThrow();
        PostDto responsePostDto=mapper.map(post,PostDto.class);
        return responsePostDto;
    }
}

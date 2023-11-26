package com.crio.starter.repository;

import java.util.Optional;
import com.crio.starter.data.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepo extends MongoRepository<Post,Integer>{
    Post findByName(String name);

    Optional<Post> findById(Long id);
}

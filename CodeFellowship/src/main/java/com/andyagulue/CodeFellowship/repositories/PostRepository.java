package com.andyagulue.CodeFellowship.repositories;

import com.andyagulue.CodeFellowship.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>{
}

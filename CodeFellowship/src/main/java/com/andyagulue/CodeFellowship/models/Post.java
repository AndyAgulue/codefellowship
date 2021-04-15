package com.andyagulue.CodeFellowship.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String body;

    @CreationTimestamp
    LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "application_user_id")
    ApplicationUser applicationUser;

    public Post(){

    }
    public Post(String body, ApplicationUser applicationUser){
        this.body = body;
        this.applicationUser = applicationUser;
    }

    public void setBody(String body){
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public String getBody(){
        return body;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    @Override
    public String toString(){
        return "Post{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", createdAt=" + createdAt +
                ", applicationUser=" + applicationUser.username +
                '}';
    }
}

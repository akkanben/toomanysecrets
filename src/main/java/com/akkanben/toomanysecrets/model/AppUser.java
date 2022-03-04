package com.akkanben.toomanysecrets.model;

import org.yaml.snakeyaml.events.Event;

import javax.persistence.*;
import java.util.List;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String username;
    String password;
    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    List<Post> postList;

    public AppUser() {
        // empty
    }

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post> getPostList() {
        return postList;
    }
}

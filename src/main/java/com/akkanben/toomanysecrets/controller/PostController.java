package com.akkanben.toomanysecrets.controller;

import com.akkanben.toomanysecrets.model.AppUser;
import com.akkanben.toomanysecrets.model.Post;
import com.akkanben.toomanysecrets.repository.AppUserRepository;
import com.akkanben.toomanysecrets.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

@Controller
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("/secret-recipes/{username}")
    public String getSecretRecipesPage(@PathVariable String username, Model m) {
        AppUser appUser = appUserRepository.findByUsername(username);
        m.addAttribute("appUser", appUser);
        return "secret-recipes.html";
    }

    @PostMapping("/secret-recipes/add-post")
    public RedirectView addNewPost(long appUserId, String postContent, String subject) {
        Date date = new Date();
        Post post = new Post(postContent, subject, date);
        AppUser appUser = appUserRepository.getById(appUserId);
        post.setAppUser(appUser);
        postRepository.save(post);
        return new RedirectView("/secret-recipes/" + appUser.getUsername());
    }




}

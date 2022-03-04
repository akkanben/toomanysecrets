package com.akkanben.toomanysecrets.controller;

import com.akkanben.toomanysecrets.model.AppUser;
import com.akkanben.toomanysecrets.repository.AppUserRepository;
import com.akkanben.toomanysecrets.repository.PostRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AppUserController {
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    PostRepository postRepository;

    @GetMapping("/sign-up")
    public String getCreateAccount() {
        return "sign-up.html";
    }

    @PostMapping("/create-account")
    public RedirectView addNewAccount(String username, String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(13));
        AppUser appUser = new AppUser(username, hashedPassword);
        appUserRepository.save(appUser);
        return new RedirectView("/");
    }

    @PostMapping("/login")
    public RedirectView loginToSecretRecipes(String username, String password) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null)
            return new RedirectView("/");
        if (BCrypt.checkpw(password, appUser.getPassword()))
            return new RedirectView("/secret-recipes/" + appUser.getUsername());
        else
            return new RedirectView("/");
    }


}

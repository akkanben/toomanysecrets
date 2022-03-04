package com.akkanben.toomanysecrets.repository;

import com.akkanben.toomanysecrets.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    public AppUser findByUsername(String username);
}

package com.segmentationfault.huceng.entity.repository;

import com.segmentationfault.huceng.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findAppUserByUsername(String username);

    @Query("select username from AppUser")
    List<String> getAllUsernames();

}

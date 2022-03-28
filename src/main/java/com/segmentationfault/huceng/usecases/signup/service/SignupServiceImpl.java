package com.segmentationfault.huceng.usecases.signup.service;

import com.segmentationfault.huceng.entity.PendingUser;
import com.segmentationfault.huceng.entity.Role;
import com.segmentationfault.huceng.entity.repository.AppUserRepository;
import com.segmentationfault.huceng.entity.repository.PendingUserRepository;
import com.segmentationfault.huceng.entity.repository.RoleRepository;
import com.segmentationfault.huceng.exception.UsernameAlreadyExists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SignupServiceImpl implements SignupService {
    private final PendingUserRepository pendingUserRepository;
    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;


    @Override
    public PendingUser saveUserAsPending(PendingUser pendingUser) {
        log.info("Pending user {}", pendingUser);
        List<String> usernames = appUserRepository.getAllUsernames();
        List<String> pendingUsernames = pendingUserRepository.getAllUsernames();
        List<String> toCheck = Stream.concat(usernames.stream(), pendingUsernames.stream()).toList();
        log.info("Usernames {}", toCheck);
        if (toCheck.contains(pendingUser.getUsername()))
            throw new UsernameAlreadyExists(pendingUser.getUsername());

        return pendingUserRepository.save(pendingUser);
    }

    /*
    Called by the Admin. Moves the user from Pending table to AppUserTable.
    delete -> save.
     */
    @Override
    public void approveUser(String username) {

    }

    @Override
    public void assignRoleToPendingUser(String username, String roleName) {
        PendingUser user = pendingUserRepository.findPendingUserByUsername(username).orElseThrow();
        Role role = roleRepository.findByName(roleName).orElseThrow();

        user.getRoles().add(role);
    }
}

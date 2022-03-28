package com.segmentationfault.huceng.usecases.signup.service;

import com.segmentationfault.huceng.entity.PendingUser;

public interface SignupService {

    PendingUser saveUserAsPending(PendingUser pendingUser);

    void approveUser(String username);

    void assignRoleToPendingUser(String username, String roleName);

}

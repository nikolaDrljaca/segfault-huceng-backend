package com.segmentationfault.huceng.entity;

/**
 * Entity to keep track of banned users. From each banned user we keep
 * email and username to prevent login and new account creation with these credentials.
 */
public class Banned {
    private String email;
    private String username;
}

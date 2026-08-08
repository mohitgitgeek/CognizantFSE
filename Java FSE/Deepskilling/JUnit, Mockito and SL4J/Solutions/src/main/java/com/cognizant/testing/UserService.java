package com.cognizant.testing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private final UserRepository repository;
    public UserService(UserRepository repository) { this.repository = repository; }
    public User getUser(long id) { return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found: " + id)); }
    public void register(User user) { repository.save(user); LOG.info("Registered user {}", user.id()); }
}

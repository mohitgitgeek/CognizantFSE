package com.cognizant.testing;

import java.util.Optional;

public interface UserRepository { Optional<User> findById(long id); void save(User user); }

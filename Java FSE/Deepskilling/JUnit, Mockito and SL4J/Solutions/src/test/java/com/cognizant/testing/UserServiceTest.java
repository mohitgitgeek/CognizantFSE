package com.cognizant.testing;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock UserRepository repository;
    @InjectMocks UserService service;
    @Test void returnsMockedUser() { User user = new User(1, "Asha"); when(repository.findById(1)).thenReturn(Optional.of(user)); assertSame(user, service.getUser(1)); verify(repository).findById(1); }
    @Test void raisesErrorForMissingUser() { when(repository.findById(2)).thenReturn(Optional.empty()); assertThrows(IllegalArgumentException.class, () -> service.getUser(2)); }
    @Test void savesRegisteredUser() { User user = new User(3, "Ravi"); service.register(user); ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class); verify(repository).save(captor.capture()); assertEquals(user, captor.getValue()); }
}

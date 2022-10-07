package dev.bozho.birthdater.service.impl;

import dev.bozho.birthdater.model.User;
import dev.bozho.birthdater.repository.UserRepository;
import dev.bozho.birthdater.repository.FriendRepository;
import dev.bozho.birthdater.utils.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "User with email %s not found";
    private final UserRepository userRepository;
    private final FriendRepository friendRepository;

    private final EmailValidator emailValidator;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                             .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public Optional<User> getUserById(long userId) {
        return userRepository.findById(userId);
    }

    public boolean doesUserExistById(long userId) {
        return getUserById(userId).isPresent();
    }

    public String signUpUser(User user) {
        boolean isEmailValid = emailValidator.test(user.getEmail());

        if(!isEmailValid) {
            throw new IllegalArgumentException("email not valid");
        }

        boolean userExists = userRepository
                .findByEmail(user.getEmail())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException("email already taken");
        }

        user.setPassword(user.getPassword());

        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        return token;
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }

}

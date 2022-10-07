package dev.bozho.birthdater.service.impl;

import dev.bozho.birthdater.model.User;
import dev.bozho.birthdater.repository.UserRepository;
import dev.bozho.birthdater.repository.FriendRepository;
import dev.bozho.birthdater.utils.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final static String USER_EMAIL_NOT_FOUND_MSG =
            "User with email %s not found";

    private final static String USERID_NOT_FOUND_MSG =
            "User with id %s not found";
    private final UserRepository userRepository;
    private final FriendRepository friendRepository;

    private final EmailValidator emailValidator;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                             .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_EMAIL_NOT_FOUND_MSG, email)));
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        if(users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No users found");
        }

        return users;
    }

    public Optional<User> getOptionalUserById(long userId) {
        return userRepository.findById(userId);
    }

    public User getUserById(long userId){
        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(USERID_NOT_FOUND_MSG, userId));
        }

        return user.get();
    }

    public boolean doesUserExistById(long userId) {
        return getOptionalUserById(userId).isPresent();
    }

    public User signUpUser(User user) {
        boolean isEmailValid = emailValidator.test(user.getEmail());
        if(!isEmailValid) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email isn't valid");

        boolean userExists = userRepository
                .findByEmail(user.getEmail())
                .isPresent();
        if (userExists) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already taken");

        userRepository.save(user);

        return user;
    }

    public HttpStatus deleteUserById(long userId) {
        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()) return HttpStatus.NOT_FOUND;

        userRepository.deleteById(userId);
        return HttpStatus.OK;
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }

}

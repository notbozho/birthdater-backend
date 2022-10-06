package dev.bozho.birthdater.service.impl;

import dev.bozho.birthdater.domain.AppUser;
import dev.bozho.birthdater.repository.AppUserRepository;
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
public class AppUserServiceImpl implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "User with email %s not found";
    private final AppUserRepository appUserRepository;
    private final FriendRepository friendRepository;

    private final EmailValidator emailValidator;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public Optional<AppUser> getUserById(long userId) {
        return appUserRepository.findById(userId);
    }

    public boolean doesUserExistById(long userId) {
        return getUserById(userId).isPresent();
    }

    public String signUpUser(AppUser appUser) {
        boolean isEmailValid = emailValidator.test(appUser.getEmail());

        if(!isEmailValid) {
            throw new IllegalArgumentException("email not valid");
        }

        boolean userExists = appUserRepository
                .findByEmail(appUser.getEmail())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException("email already taken");
        }

        appUser.setPassword(appUser.getPassword());

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        return token;
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }

}

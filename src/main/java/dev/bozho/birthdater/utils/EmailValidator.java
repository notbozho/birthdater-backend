package dev.bozho.birthdater.utils;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Service
public class EmailValidator implements Predicate<String> {

    String regex = "^(.+)@(.+)$";
    Pattern pattern = Pattern.compile(regex);

    @Override
    public boolean test(String email) {
        return pattern.matcher(email).matches();
    }
}

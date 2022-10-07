package dev.bozho.birthdater.service;

public interface IEmailService {

    void send(String to, String subject, String emailContent);
}

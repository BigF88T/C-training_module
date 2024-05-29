package ru.safonov.trainingmodule.BackEnd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.safonov.trainingmodule.BackEnd.models.User;
import ru.safonov.trainingmodule.BackEnd.repositories.UsersRepository;

import java.util.Date;

@Service
public class RegistrationService {

    private  final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));   // Реализация шифрования пароля с помощью BCrypt
        user.setRole("ROLE_USER"); // Назначение роли "User" при регистрации
        user.setLastVisit(new Date());
        usersRepository.save(user);
    }
}

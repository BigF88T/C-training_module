package ru.safonov.trainingmodule.BackEnd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.safonov.trainingmodule.BackEnd.models.User;
import ru.safonov.trainingmodule.BackEnd.repositories.UsersRepository;
import ru.safonov.trainingmodule.BackEnd.security.UsersDetails;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.repository.util.ClassUtils.ifPresent;
import static org.springframework.util.ClassUtils.isPresent;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = usersRepository.findByUsername(s);

        if (user.isEmpty())
            throw new UsernameNotFoundException("Человек не найден!");

        return new UsersDetails(user.get());
    }

    public List<User> allUsers() {
        return usersRepository.findAll();
    }

    public boolean deleteUser(int id) {
        if (usersRepository.findById(id).isPresent()) {
            usersRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public void setLastVisit(int id, User loginUser) {
        usersRepository.findById(id).ifPresent(
                user -> {
                    loginUser.setLastVisit(new Date());
                }
        );
    }
}

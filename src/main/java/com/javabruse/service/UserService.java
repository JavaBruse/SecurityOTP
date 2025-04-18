package com.javabruse.service;

import com.javabruse.controllers.AdminController;
import com.javabruse.domain.model.Role;
import com.javabruse.domain.model.User;
import com.javabruse.exaption.ExistsExeption;
import com.javabruse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    public User save(User user) {
        return repository.save(user);
    }

    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new ExistsExeption("Пользователь с \"" + user.getUsername() + "\" таким именем уже существует");
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new ExistsExeption("Пользователь с  таким \"" + user.getEmail() + "\"  email уже существует");
        }

        return save(user);
    }

    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        List<User> list = repository.findAll().stream().filter(x -> x.getRole().equals(Role.ROLE_ADMIN)).toList();
        if (!list.isEmpty()) {
            log.info("Пользователь Admin уже существует.");
            return;
        }
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }
}

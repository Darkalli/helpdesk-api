package com.services;

import com.dtos.PasswordUpdateDto;
import com.dtos.UserRegisterDto;
import com.dtos.UserUpdateDto;
import com.entities.Admin;
import com.entities.Agent;
import com.entities.Client;
import com.entities.User;
import com.enums.Profile;
import com.repositories.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Transactional
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public User registerUser(UserRegisterDto newUser){

        if (newUser.role() == null || !Profile.isValid(newUser.role())){
            throw new IllegalArgumentException("Perfil de usuário inválido ou não informado!");
        }
        boolean checkEmail = usersRepository.existsByEmail(newUser.email());
        if (checkEmail){
            throw new IllegalArgumentException("Este email já existe!");
        }
        User user;

        switch (newUser.role()) {
            case ADMIN -> user = new Admin();
            case AGENT -> user = new Agent();
            case CLIENT -> user = new Client();
            default -> throw new IllegalArgumentException("Perfil inválido");
        }

        user.setName(newUser.name());
        user.setEmail(newUser.email());
        user.setPassword(passwordEncoder.encode(newUser.password()));
        user.setRole(newUser.role());

        return usersRepository.save(user);
    }

    public User getById(Long id){
        Optional<User> userFind = usersRepository.findById(id);
        User user;
        if (userFind.isPresent()) {
            return user = userFind.get();
        }
        return null;
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateUser(UserUpdateDto updatedUser){

        User oldUser = usersRepository.findById(updatedUser.userId()).orElseThrow(() -> new EntityNotFoundException("Não existe usuario com o id: " + updatedUser.userId()));

        oldUser.setName(updatedUser.name());
        oldUser.setEmail(updatedUser.email());

        usersRepository.save(oldUser);
    }
    @Transactional
    public void updatePassword(PasswordUpdateDto password){
        User user = getById(password.userId());
        if(passwordEncoder.matches(password.oldPassword(), user.getPassword())){
            String safePassword = passwordEncoder.encode(password.newPassword());
            user.setPassword(safePassword);
            usersRepository.save(user);
        }
    }
}

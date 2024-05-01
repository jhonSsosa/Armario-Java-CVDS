package eci.cvds.armario.service;

import eci.cvds.armario.repository.UserRepository;
import eci.cvds.armario.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public boolean validarUsuario(String username, String password){
        Optional<User> user = userRepository.findByUsername(username);
        return user.get().getUsername().equals(username) && user.get().getPassword().equals(password);
    }
    public List<User> getAllUsers() {return userRepository.findAll();}
}

package eci.cvds.armario.service;

import eci.cvds.armario.repository.UserRepository;
import eci.cvds.armario.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public boolean validarUsuario(String username, String password){
        User user = userRepository.findByUsername(username);
        return user.getUsername().equals(username) && user.getPassword().equals(password);
    }
    public User getUserByUsername(String username){return userRepository.findByUsername(username);}

    public List<User> getAllUsers() {return userRepository.findAll();}
}

package eci.cvds.armario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import eci.cvds.armario.model.User;
import eci.cvds.armario.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void adicionar(User user) {
        userRepository.save(user);
    }

    public boolean validarUsuario(User logInUser) {
        User user = userRepository.findByUsername(logInUser.getUsername());
        if (user == null) {
            return false;
        }

        // Usar BCrypt para comparar las contraseñas
        return user.getUsername().equals(logInUser.getUsername()) &&
               passwordEncoder.matches(logInUser.getPassword(), user.getPassword());
    }

    public User actualizar(String id, User updatedUser) {
        User user = userRepository.findById(id).get();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(updatedUser.getRole());
        user.setUsername(updatedUser.getUsername());
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserById(String id) throws ClassNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isEmpty())
            return user.get();
        else{
            throw new ClassNotFoundException();
        }
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void eliminarUsuario(String id) {
        userRepository.deleteById(id);
    }
}

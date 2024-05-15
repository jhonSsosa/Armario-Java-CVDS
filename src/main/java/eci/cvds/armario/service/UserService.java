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
    public void adicionar(User user){userRepository.save(user);}
    public boolean validarUsuario(User LogInUser){
        User user = null;
        if(userRepository.findByUsername(LogInUser.getUsername()) == null){
            return false;
        }else{
            user = userRepository.findByUsername(LogInUser.getUsername());
        }
        return user.getUsername().equals(LogInUser.getUsername()) &&
                user.getPassword().equals(LogInUser.getPassword());
    }
    public User getUserByUsername(String username){return userRepository.findByUsername(username);}
    public User getUserById(String id){return userRepository.getReferenceById(id);}


    public List<User> getAllUsers() {return userRepository.findAll();}

    public void eliminarUsuario(String id){userRepository.deleteById(id);}
}

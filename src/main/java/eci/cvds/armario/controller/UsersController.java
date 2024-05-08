package eci.cvds.armario.controller;

import eci.cvds.armario.model.User;
import eci.cvds.armario.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/")
public class UsersController {
    private UserService userService;
    @Autowired
    public UsersController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return this.userService.getAllUsers();
    }
    @GetMapping("/user/{username}")
    public User getUser(@PathVariable("username") String username){
        return this.userService.getUserByUsername(username);
    }
    @PostMapping("/adicionarUsuario")
    public void adicionar(@RequestBody User user){userService.adicionar(user);}
    @PostMapping("/chequearUsuario")
    public boolean validarUsuario(@RequestBody User user){
        return this.userService.validarUsuario(user);
    }

    @DeleteMapping("/eliminarUsuario")
    public void eliminarUsuario(@PathVariable String id){userService.eliminarUsuario(id);}
}

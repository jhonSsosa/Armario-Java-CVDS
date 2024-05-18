package eci.cvds.armario.controller;

import eci.cvds.armario.model.Session;
import eci.cvds.armario.model.User;
import eci.cvds.armario.repository.SessionRepository;
import eci.cvds.armario.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://witty-field-0ab72731e.5.azurestaticapps.net"})
@RequestMapping(value = "/user")
public class UsersController {
    private UserService userService;
    private SessionRepository sessionRepository;

    @Autowired
    public UsersController(UserService userService, SessionRepository sessionRepository) {
        this.userService = userService;
        this.sessionRepository = sessionRepository;
        ;
    }

    @GetMapping("")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/admin/users")
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/admin/username/{id}")
    public User getUserByUsername(@PathVariable("id") String id) {
        return this.userService.getUserById(id);
    }

    @GetMapping("/client/userId")
    public User getUserByID(@CookieValue("authToken") UUID id) {
        User user = this.sessionRepository.getReferenceById(id).getUser();
        return user;
    }

    @PostMapping("/admin/adicionarUsuario")
    public void adicionar(@RequestBody User user) {
        userService.adicionar(user);
    }

    @PostMapping("/admin/chequearUsuario")
    public boolean validarUsuario(@RequestBody User user) {
        return this.userService.validarUsuario(user);
    }

    @DeleteMapping("/admin/eliminarUsuario/{id}")
    public void eliminarUsuario(@PathVariable String id) {
        userService.eliminarUsuario(id);
    }
}

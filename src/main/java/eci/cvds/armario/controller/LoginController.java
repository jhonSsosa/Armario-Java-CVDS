package eci.cvds.armario.controller;

import eci.cvds.armario.model.Session;
import eci.cvds.armario.model.User;
import eci.cvds.armario.repository.SessionRepository;
import eci.cvds.armario.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:3000", "https://witty-field-0ab72731e.5.azurestaticapps.net"})
@RestController
@RequestMapping(value = "/login")
public class LoginController {
    private static final String LOGIN_PAGE = "login/login";
    private final UserService userService;
    private final SessionRepository sessionRepository;

    @Autowired
    public LoginController(UserService userService, SessionRepository sessionRepository) {
        this.userService = userService; this.sessionRepository = sessionRepository;}

    @PostMapping("")
    public Session loginSubmit(@RequestBody User userSend) {
        if (!userService.validarUsuario(userSend)) {
            return null;
        } else {
            User user = userService.getUserByUsername(userSend.getUsername());
            Session session = new Session(UUID.randomUUID(), Instant.now(), user);
            sessionRepository.save(session);
            // create and add a cookie to the response
            return session;
        }
    }

    @PostMapping("logout")
    public String logoutSubmit(HttpServletResponse response) {
        Cookie cookie = new Cookie("authToken", null);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return LOGIN_PAGE;
    }
    @PostMapping("register")
    public String registerSubmit(@RequestBody User userSend){
        userService.adicionar(userSend);
        return "redirect:/login";
    }
}

package eci.cvds.armario.controller;

import eci.cvds.armario.model.Prenda;
import eci.cvds.armario.model.Session;
import eci.cvds.armario.model.User;
import eci.cvds.armario.repository.PrendaRepository;
import eci.cvds.armario.repository.SessionRepository;
import eci.cvds.armario.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://witty-field-0ab72731e.5.azurestaticapps.net"})
@RequestMapping(value = "/user")
public class PrendaController {
    private PrendaRepository prendaRepository;

    private SessionRepository sessionRepository;

    @Autowired
    public PrendaController(PrendaRepository prendaRepository, SessionRepository sessionRepository) {
        this.prendaRepository = prendaRepository;
        this.sessionRepository = sessionRepository;
    }

    @GetMapping("/client/prendas")
    public List<Prenda> getAllPrendasOfUser(@RequestHeader("authToken") UUID authToken) {
        User user = this.sessionRepository.findByToken(authToken).getUser();
        return prendaRepository.findByUser(user);
    }

    @GetMapping("/client/prenda/{idPrenda}")
    public ResponseEntity<Prenda> getPrendaById(@RequestHeader("authToken") UUID authToken, @PathVariable("idPrenda") UUID idPrenda) {
        User user = this.sessionRepository.findByToken(authToken).getUser();
        Prenda prenda = prendaRepository.findById(idPrenda).get();
        if (prenda.getUser() == user) {
            return new ResponseEntity<>(prenda, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/client/prenda")
    public Prenda addPrenda(@RequestBody Prenda prenda, @RequestHeader("authToken") UUID authToken) {
        User user = this.sessionRepository.findByToken(authToken).getUser();
        prenda.setUser(user);
        return prendaRepository.save(prenda);
    }

}

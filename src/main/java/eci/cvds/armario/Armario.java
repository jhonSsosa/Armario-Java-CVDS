package eci.cvds.armario;

import eci.cvds.armario.controller.UsersController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class Armario
{
    private final UsersController usersController;
    @Autowired
    public Armario(UsersController usersController){
        this.usersController = usersController;
    }
    public static void main( String[] args )
    {
        SpringApplication.run(Armario.class, args);
    }

}

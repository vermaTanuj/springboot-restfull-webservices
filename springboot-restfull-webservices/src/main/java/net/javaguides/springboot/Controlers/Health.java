package net.javaguides.springboot.Controlers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class Health {
    @GetMapping          //localhost:8080/health
    public String healthCheck(){
        return "Health ok";
    }
}

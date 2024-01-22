package com.example.wishslackapp.slack;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/_monitoring/health")
public class HealthController {

    @GetMapping
    @ResponseStatus(OK)
    public String health() {
        return "OK";
    }
}

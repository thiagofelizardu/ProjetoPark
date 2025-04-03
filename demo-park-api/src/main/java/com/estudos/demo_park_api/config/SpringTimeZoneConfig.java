package com.estudos.demo_park_api.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration//serve para indicar que é uma classe de configuracao
public class SpringTimeZoneConfig {

    @PostConstruct
// Indica ao Spring que, após a execução do construtor, o próximo método a ser executado será este: timeZoneConfig.
    public void timeZoneConfig() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
    }
}
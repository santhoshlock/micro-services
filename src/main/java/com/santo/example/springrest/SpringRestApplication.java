package com.santo.example.springrest;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class SpringRestApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(SpringRestApplication.class, args);
	}
	
	@Bean
	public LocaleResolver localeResolver() 
	{
		SessionLocaleResolver lc = new SessionLocaleResolver();
		lc.setDefaultLocale(Locale.US  );
		return lc;

	}

}

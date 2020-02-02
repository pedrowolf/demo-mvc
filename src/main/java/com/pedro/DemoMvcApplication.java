package com.pedro;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
public class DemoMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoMvcApplication.class, args);
		System.out.println("senha:"+new BCryptPasswordEncoder().encode("pedro123"));
	}

	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
} 

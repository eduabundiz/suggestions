package com.gendra.suggestion;

import com.gendra.suggestion.service.CitiesReaderService;
import com.gendra.suggestion.service.SuggestionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SuggestionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuggestionApplication.class, args)
		;
	}

}

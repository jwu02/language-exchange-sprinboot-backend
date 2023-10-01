package com.example.languageexchangebackend;

import com.example.languageexchangebackend.model.Language;
import com.example.languageexchangebackend.service.LanguageService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class LanguageExchangeBackendApplication {

	@Value("${exchangeable-languages-list.file-path}")
	private String languagesFilePath;

	public static void main(String[] args) {
		SpringApplication.run(LanguageExchangeBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(LanguageService languageService) {
		// this runner allows us to do something right when
		// the application starts up
		return args -> {
			// if never initialised languages repository with JSON data
			if (!languageService.hasLanguageRecords()) {
				// read JSON to db
				Gson gson = new Gson();
				Type collectionType = new TypeToken<List<Language>>(){}.getType();
				JsonReader reader = new JsonReader(new FileReader(languagesFilePath));
				List<Language> languages = gson.fromJson(reader, collectionType);

				languageService.save(languages);
				System.out.println("Languages saved!");
			}
		};
	}

}

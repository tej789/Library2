package com.example.library;
import com.example.library.model.*;
import com.example.library.component.*;
import com.example.library.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {

		SpringApplication.run(LibraryApplication.class, args);
	}
@Bean
		CommandLineRunner run(BookService bs){
			return args->{
				Book book = new Book(1,"x","y",300);
				bs.addBook(book);
			};
		}


}

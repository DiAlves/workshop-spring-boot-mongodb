package com.educandoweb.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.workshopmongo.domain.User;

//Dizendo que é um recurso REST
@RestController
@RequestMapping(value = "/users")
public class UserRedource {
	
	//Dizendo que vai ser um end-point nesse caminho -> /users
	@RequestMapping(method = RequestMethod.GET)	// OU @GetMapping
	
	//ResponseEntity -> encapsula a estrutura necessaria para retorna respostas htttp
	public ResponseEntity<List<User>> findAll() {
		User maria = new User("1", "Maria Brown", "maria@gmail.com");
		User alex = new User("2", "Alex Green", "alex@gmail.com");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(maria, alex));
		return ResponseEntity.ok().body(list);
	}
}

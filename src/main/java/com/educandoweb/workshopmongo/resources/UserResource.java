package com.educandoweb.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.workshopmongo.domain.User;
import com.educandoweb.workshopmongo.dto.UserDTO;
import com.educandoweb.workshopmongo.services.UserService;

//Dizendo que é um recurso REST
@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	//Dizendo que vai ser um end-point nesse caminho -> /users
	@RequestMapping(method = RequestMethod.GET)	// OU @GetMapping
	
	//ResponseEntity -> encapsula a estrutura necessaria para retorna respostas htttp
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAll();
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(listDTO);
	}
	
	// / mais um valor do id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	// Path Variable -> pegar a variavel do url
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@RequestMapping(method = RequestMethod.POST) // Ou @PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO) {
		User obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		
		// Colocar na resposta um header com o URI do recurso inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		//Cria um codigo 201
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	// Path Variable -> pegar a variavel do url
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}

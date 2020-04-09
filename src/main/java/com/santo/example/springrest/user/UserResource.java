package com.santo.example.springrest.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService daoService;
	
	@GetMapping("/users")
	public List<User> retriveAllUsers()
	{
		return daoService.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id )
	{
		User newUser =  daoService.findOne(id);
		if( newUser == null)
			throw new UserNotFoundException("id - "+id);
		return newUser;
	}
	
/*	@GetMapping("/userhateos/{id}")
	public EntityModel<User> retrieveUserWithHateos(@PathVariable int id )
	{
		User newUser =  daoService.findOne(id);
		if( newUser == null)
			throw new UserNotFoundException("id - "+id);
		
		EntityModel<User> model = new EntityModel<>(newUser);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUsers());
		model.add(linkTo.withRel("all-users"));
		
		return model;
	}*/
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user )
	{
		User newUser = daoService.addUser(user);
		
		URI location =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
												   .buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	

	
}

package com.santo.example.springrest.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJPAResource {
	
	@Autowired
	private UserDaoService daoService;
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	
	@GetMapping("/jpa/users")
	public List<User> retriveAllUsers()
	{
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public User retrieveUser(@PathVariable int id )
	{
		Optional<User> newUser =  userRepository.findById(id);
		if( !newUser.isPresent() )
			throw new UserNotFoundException("id - "+id);
		return newUser.get();
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePosts(@PathVariable int id )
	{
		Optional<User> newUser =  userRepository.findById(id);
		if( !newUser.isPresent() )
			throw new UserNotFoundException("id - "+id);
		return newUser.get().getPosts();
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
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user )
	{
		User newUser = userRepository.save(user);
		
		URI location =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
												   .buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id , @RequestBody Post post )
	{
		
		Optional<User> newUser =  userRepository.findById(id);
		if( !newUser.isPresent() )
			throw new UserNotFoundException("id - "+id);
		User getUser =  newUser.get();
		
		post.setUser(getUser);
		
		Post save = postRepository.save(post);
		
		URI location =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
												   .buildAndExpand(save.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	

	
}

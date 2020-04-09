package com.santo.example.springrest.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController   
{

	@RequestMapping(method=RequestMethod.GET,path="/hello-world") //can use @GetMapping instead of giving method = -- 
	public String helloWorld()
	{
		return "Hello World";
	}
	
	@GetMapping(path="/hello-world-bean") 
	public HelloWorldBean helloWorldBean()
	{
		return new HelloWorldBean("Hello World");
	}
	
	@GetMapping(path="/hello-world-bean/path-variable/{name}") 
	public HelloWorldBean helloWorldBean(@PathVariable String name )
	{
		return new HelloWorldBean(String.format("Hello World %s",name ) );
	}
}

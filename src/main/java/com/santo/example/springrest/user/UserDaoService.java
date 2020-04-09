package com.santo.example.springrest.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	private static int user_count = 3;
	
	static
	{
		users.add(new User("santo",1,new Date() ));
		users.add(new User("sanitha",2,new Date() ));
		users.add(new User("Badri",3,new Date() ));
	}
	
	public List<User> findAll()
	{
		return users;
	}
	
	public User addUser(User user)
	{
		if( user.getId() == null )
		{
			user.setId(++user_count);
		}
		users.add(user);
		
		return user;
	}
	
	public User findOne(int id )
	{
		for (User user : users )
		{
			if( user.getId() == id )
			{
				return user;
			}
		}
		return null;
	}
	
	

	
}

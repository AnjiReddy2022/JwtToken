package com.filpcart.customer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.filpcart.customer.Model.MyUser;
import com.filpcart.customer.repository.MyUserRepository;

@Service
public class MyUserDetailsService {
	
	@Autowired
	private MyUserRepository myUserRepository;
	
	public UserDetails loadUserByUserName(String username) throws UsernameNotFoundException{
		Optional<MyUser> user=myUserRepository.findByUsername(username);
		if(user.isPresent()) {
			var userObj=user.get();
			return User.builder()
					.username(userObj.getUserName())
					.password(userObj.getPassword())
					.roles(getRoles(userObj))
					.build();
			
		}else {
			throw new UsernameNotFoundException(username);
		}
		
	}

	private String[] getRoles(MyUser user) {
		if(user.getRole()==null) {
			return new String[] {"USER"};
		}
		return user.getRole().split(",");

	}

}

package com.socialnetwork.main.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.socialnetwork.main.model.User;
import com.socialnetwork.main.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByEmailId(username);

		Collection<GrantedAuthority> list = new ArrayList<>(); 
		SimpleGrantedAuthority sga = new SimpleGrantedAuthority(user.getUserrole()); 
		list.add(sga);

		org.springframework.security.core.userdetails.User springUser = new 
				org.springframework.security.core.userdetails.User
				(user.getEmailId(),user.getPassword(),list);
		return springUser;
	}
	

}
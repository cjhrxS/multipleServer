package ua.ita.smartcarservice.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.service.UserPrinciple;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	/**
	 *  Method finds a record from users 
	 *  database tables to build a UserDetails object 
	 *  for authentication.
	 */
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity userEntity = userRepository.findByUsername(username).get();
		
		return UserPrinciple.build(userEntity);
	}
	
}

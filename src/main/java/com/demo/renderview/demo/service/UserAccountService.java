package com.demo.renderview.demo.service;


import com.demo.renderview.demo.repository.AuthorityRepository;
import com.demo.renderview.demo.repository.UserRepository;
import com.demo.renderview.demo.repository.entity.Authority;
import com.demo.renderview.demo.repository.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserAccountService implements UserDetailsService {

	private final Logger log = LoggerFactory.getLogger(UserAccountService.class);
	private final UserRepository userRepository;

	private final AuthorityRepository authorityRepository;

	public UserAccountService(UserRepository userRepository, AuthorityRepository authorityRepository) {
		this.userRepository = userRepository;
		this.authorityRepository = authorityRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = this.userRepository.findByUsername(username);
		User user = optionalUser.orElseGet(User::new);
		log.error("User {}", user);
		return user;

	}

	private Collection<? extends GrantedAuthority> getAuthorities() {
		List<Authority> authoritiesList = new ArrayList<>();
		return authoritiesList;
	}



	public Optional<User> getUserById(long id) {
		return Optional.empty();
	}


	public Optional<User> getByEmail(String email) {
		return Optional.empty();
	}


	public List<User> getAllUsers() {

		List<User> users = userRepository.findAll();
		users.parallelStream().forEach(user -> user.setRoles(user.getAuthorities().stream().map(authority -> authority.getAuthority()).collect(Collectors.toSet())));
		return users;
	}


	public User create(User userDTO) {
		return null;
	}


	public User getUserByUsername(String username) {
		return null;
	}


	public Collection<Authority> getRoles() {
		return null;
	}


	public List<User> getUsersWithDetail() {
		return null;
	}


	public User update(User user) {
		return null;
	}


	public Optional<User> getUserByNameWithDetail(String name) {
		return userRepository.findByUserNameWithDetail(name);
	}


	public Authority createAuthority(Authority authority) {
		return null;
	}


	public Authority updateAuthority(Authority authority) {
		return null;
	}


	public Authority getAuthorityById(Long id) {
		return null;
	}


	public void deleteAuthority(Authority authority, Collection<User> users) {

	}


	public Collection<User> getUsersByAuthorityId(Long authorityId) {
		return null;
	}


	public void delete(Long id) {
		userRepository.delete(id);
	}

}

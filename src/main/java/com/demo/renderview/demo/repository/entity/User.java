package com.demo.renderview.demo.repository.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class User implements UserDetails, Serializable {

	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, unique = true)
	private String username;

	@JsonIgnore
	private String password;

	private boolean enabled;

	@Transient
	private Set<String> roles;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_authorities",
			joinColumns = @JoinColumn(
					name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
					name = "authority_id", referencedColumnName = "id"))
	private Collection<Authority> authorities;


	public User() {
	}

	public User(String username, String password, boolean enabled, Collection<Authority> authorities) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authorities = authorities;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = PASSWORD_ENCODER.encode(password);
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAuthorities(Collection<Authority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<Authority> getAuthorities() {
		return authorities;
	}


	@Override
	public boolean isEnabled() {
		return enabled;
	}

	//todo fix these implementations
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
}

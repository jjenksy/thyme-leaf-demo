package com.demo.renderview.demo.repository.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
@Getter
@Setter
@ToString
public class Authority implements GrantedAuthority {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String authority;


	public Authority() {
	}

	public Authority(String authority) {

		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}
}

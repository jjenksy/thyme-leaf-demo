package com.demo.renderview.demo;

import com.demo.renderview.demo.events.OnRegistrationCompleteEvent;
import com.demo.renderview.demo.models.Roles;

import com.demo.renderview.demo.repository.AuthorityRepository;
import com.demo.renderview.demo.repository.UserRepository;
import com.demo.renderview.demo.repository.entity.Authority;
import com.demo.renderview.demo.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.Arrays;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}



	/**
	 * Initialize with basic admin user and Authorities
	 * @param userAccountRepo
	 * @param authorityRepository
	 * @return
	 */
	@Bean
	CommandLineRunner init(final UserRepository userAccountRepo, final AuthorityRepository authorityRepository, final ApplicationEventPublisher eventPublisher) {

		return new CommandLineRunner() {

			@Override
			public void run(String... arg0) throws Exception {
				//create the two authorities
				createAuthorityIfNotFound(Roles.ADMIN);
				createAuthorityIfNotFound(Roles.USER);
				Authority adminRole = authorityRepository.findByAuthority(Roles.ADMIN);
				Authority userRole = authorityRepository.findByAuthority(Roles.USER);
				//create the admin user
				User admin = new User();
				User user = new User();

				user.setPassword("user");
				user.setUsername("user");
				user.setEnabled(true);
				user.setFirstLogin(false);
				user.setAuthorities(Arrays.asList(userRole));

				userAccountRepo.save(user);
				//rolesSet.add(roles);
				admin.setPassword("admin");
				admin.setUsername("admin");
				admin.setEnabled(false);
				admin.setAuthorities(Arrays.asList(adminRole,userRole));

				userAccountRepo.save(admin);

				try {
					eventPublisher.publishEvent(new OnRegistrationCompleteEvent(admin));
				} catch (Exception e){
					System.out.println(e.getMessage());
				}



			}
			@Transactional
			private Authority createAuthorityIfNotFound(String name) {

				Authority role = authorityRepository.findByAuthority(name);
				if (role == null) {
					role = new Authority(name);
					authorityRepository.save(role);
				}
				return role;
			}

		};
	}

}




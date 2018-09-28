package com.demo.renderview.demo.repository;

import com.demo.renderview.demo.repository.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	User save(User user) throws DataAccessException;

	void delete(User user) throws DataAccessException;

	boolean exists(Long userId) throws DataAccessException;

	@Query("select distinct u from User u left join fetch " +
			"u.authorities")
	List<User> getUsersWithDetail();

	@Query("select distinct u from User u left join fetch u.authorities where u.username = ?1")
	Optional<User> findByUserNameWithDetail(String name);


	@Query("select distinct u from User u left join u.authorities a where a.id = ?1")
	List<User> findByAuthorityId(Long id);
}

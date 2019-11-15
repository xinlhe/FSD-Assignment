package com.ibm.springframework.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ibm.springframework.assignment.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	@Query(value = "SELECT u.id, u.name, u.email, u.username, u.password FROM t_user u WHERE u.id = ?1", nativeQuery = true)
	User getUserInfoById(String uid);
}

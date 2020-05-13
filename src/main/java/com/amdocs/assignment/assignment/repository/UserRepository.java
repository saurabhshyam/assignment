package com.amdocs.assignment.assignment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.amdocs.assignment.assignment.vo.UserAuthData;
@Repository
public interface UserRepository extends CrudRepository<UserAuthData, String>{
	 public UserAuthData findByName(String user);
}

package com.amdocs.assignment.assignment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.amdocs.assignment.assignment.vo.ProfileServiceData;

@Repository

public interface ProfileServiceRepository extends CrudRepository<ProfileServiceData, String> {
	
}

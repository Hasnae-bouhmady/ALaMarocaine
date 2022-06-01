package com.bridgelabz.alamarocaine.repository;

import org.springframework.data.repository.CrudRepository;

import com.bridgelabz.alamarocaine.entity.Chef;

public interface ChefInterface extends CrudRepository<Chef, Long> {

	// h List<Chef> findByStatus(String status);

	Chef findByChefId(Long chefId);

}

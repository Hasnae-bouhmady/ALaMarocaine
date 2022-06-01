package com.bridgelabz.alamarocaine.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bridgelabz.alamarocaine.entity.Plat;

public interface PlatInterface extends CrudRepository<Plat, Long> {

	List<Plat> findByStatus(String status);

	Plat findByPlatId(Long platId);

}

package com.bridgelabz.alamarocaine.repository;

import java.util.List;

import com.bridgelabz.alamarocaine.entity.Chef;

public interface IChef {

	Chef save(Chef chefinformation);

	List<Chef> getUsers();

}

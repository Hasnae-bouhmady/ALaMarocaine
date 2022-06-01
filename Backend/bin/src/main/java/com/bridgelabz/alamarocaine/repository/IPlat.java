package com.bridgelabz.alamarocaine.repository;

import java.util.List;

import com.bridgelabz.alamarocaine.entity.Plat;

public interface IPlat {

	Plat save(Plat platinformation);

	List<Plat> getUsers();

}

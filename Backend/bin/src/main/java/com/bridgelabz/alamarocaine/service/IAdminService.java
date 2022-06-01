package com.bridgelabz.alamarocaine.service;

import java.util.List;

import com.bridgelabz.alamarocaine.entity.Plat;

public interface IAdminService {

	boolean verifyPlat(long platId, String staus, String token);

	// h boolean verifyChef(long chefId, String staus, String token);

	List<Plat> getPlatsByStatus(String status);

	// h List<Chef> getChefsByStatus(String status);
}

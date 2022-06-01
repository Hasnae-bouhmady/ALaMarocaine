package com.bridgelabz.alamarocaine.service;

//tous les commentaires sont les miens
import java.util.List;

import com.bridgelabz.alamarocaine.dto.ChefDto;
import com.bridgelabz.alamarocaine.dto.EditChefDto;
//import com.bridgelabz.alamarocaine.dto.RatingReviewDTO;
import com.bridgelabz.alamarocaine.entity.Chef;
//import com.bridgelabz.alamarocaine.entity.ReviewAndRating;

public interface IChefService {

	boolean addChefs(String imageName, ChefDto information, String token);

	List<Chef> getChefInfo(String token);//

	List<Chef> sortGetAllChefs();//

	// List<Plat> sorting(boolean value);

	// List<Plat> findAllPageBySize(int pagenumber);

	Chef getChefbyId(Long chefId);//

	// Plat getTotalPriceofPlat(Long platId, long quantity);

	boolean editChef(long chefId, EditChefDto information, String token);

	boolean deleteChef(long chefId, String token);

	// List<Plat> getAllAprovedPlat();

	// boolean editPlatStatus(long platId, String status, String token);

	// List<Plat> getAllOnHoldPlats(String token);

	// List<Plat> getAllRejectedPlats(String token);

	// boolean writeReviewAndRating(String token, RatingReviewDTO rrDTO, Long
	// platId);

	// List<ReviewAndRating> getRatingsOfPlat(Long platId);

	// Integer getPlatsCount();

	// double avgRatingOfPlat(Long platId);

	// Page<Plat> getPlatAproval(Optional<String> searchBy, Optional<Integer> page,
	// Optional<String> sortBy,
	// Optional<String> order);

	boolean uploadChefImage(long chefId, String imageName, String token);

	// List<Plat> sortPlatByRate();
}
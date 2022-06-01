package com.bridgelabz.alamarocaine.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.bridgelabz.alamarocaine.dto.EditPlatDto;
import com.bridgelabz.alamarocaine.dto.PlatDto;
import com.bridgelabz.alamarocaine.dto.RatingReviewDTO;
import com.bridgelabz.alamarocaine.entity.Plat;
import com.bridgelabz.alamarocaine.entity.ReviewAndRating;

public interface IPlatService {

	boolean addPlats(String imageName, PlatDto information, String token);

	List<Plat> getPlatInfo(String token);//

	List<Plat> sortGetAllPlats();//

	List<Plat> sorting(boolean value);

	List<Plat> findAllPageBySize(int pagenumber);

	Plat getPlatbyId(Long platId);//

	Plat getTotalPriceofPlat(Long platId, long quantity);

	boolean editPlat(long platId, EditPlatDto information, String token);

	boolean deletePlat(long platId, String token);

	List<Plat> getAllAprovedPlat();

	boolean editPlatStatus(long platId, String status, String token);

	List<Plat> getAllOnHoldPlats(String token);

	List<Plat> getAllRejectedPlats(String token);

	boolean writeReviewAndRating(String token, RatingReviewDTO rrDTO, Long platId);

	List<ReviewAndRating> getRatingsOfPlat(Long platId);

	Integer getPlatsCount();

	double avgRatingOfPlat(Long platId);

	Page<Plat> getPlatAproval(Optional<String> searchBy, Optional<Integer> page, Optional<String> sortBy,
			Optional<String> order);

	boolean uploadPlatImage(long platId, String imageName, String token);

	List<Plat> sortPlatByRate();
}
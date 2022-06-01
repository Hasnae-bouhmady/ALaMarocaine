package com.bridgelabz.alamarocaine.service;

import java.util.List;

import com.bridgelabz.alamarocaine.entity.WishlistPlat;

public interface IWishlistService {
	List<WishlistPlat> addwishPlat(String token, long platId);

	List<WishlistPlat> getWishlistPlats(String token);

	boolean removeWishPlat(String token, Long platId);

	int getCountOfWishlist(String token);

}

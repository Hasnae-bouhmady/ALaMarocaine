package com.bridgelabz.alamarocaine.service;

import java.util.List;

import com.bridgelabz.alamarocaine.dto.CartDto;
import com.bridgelabz.alamarocaine.entity.CartItem;

public interface ICartService {
	List<CartItem> addPlattoCart(String token, long platId);

	List<CartItem> getPlatsfromCart(String token);

	boolean removePlatsFromCart(String token, Long platId);

	int getCountOfPlats(String token);

	CartItem IncreasePlatsQuantityInCart(String token, Long platId, CartDto platQuantityDetails);

	CartItem descreasePlatsQuantityInCart(String token, Long platId, CartDto platQuantityDetails);

}

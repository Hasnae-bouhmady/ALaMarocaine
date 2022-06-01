package com.bridgelabz.alamarocaine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.alamarocaine.entity.WishlistPlat;
import com.bridgelabz.alamarocaine.response.Response;
import com.bridgelabz.alamarocaine.service.IWishlistService;

@RestController

@CrossOrigin("*")
public class WishlistController {
	@Autowired
	private IWishlistService wishplatService;

	@PostMapping("alamarocaine/v3/wishlist/addplatWishlist/{platId}")
	public ResponseEntity<Response> addPlatsToWish(@RequestHeader String token, @PathVariable long platId)
			throws Exception {
		List<WishlistPlat> wishplat = wishplatService.addwishPlat(token, platId);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Response("le plat est ajouté à la liste des favoris", 200, wishplat));
	}

	@GetMapping("alamarocaine/v3/wishlist/getwishplats")
	public ResponseEntity<Response> getPlatsfromWish(@RequestHeader(name = "token") String token) throws Exception {
		List<WishlistPlat> wishplat = wishplatService.getWishlistPlats(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(" wishlist Bag is fetched", 200, wishplat));
	}

	@DeleteMapping("alamarocaine/v3/wishlist/removeWishlist/{platId}")
	public ResponseEntity<Response> removePlatsToWish(@RequestHeader String token, @PathVariable Long platId)
			throws Exception {
		boolean wishplat = wishplatService.removeWishPlat(token, platId);
		if (wishplat != false) {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new Response("Le plat est supprimé de la liste des favoris", 200, wishplat));
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Aucun plat à supprimé !", 200, wishplat));

	}

	@GetMapping("alamarocaine/v3/wishlist/wishlistcount")
	public ResponseEntity<Response> getWishPlatsCount(@RequestHeader(name = "token") String token) throws Exception {
		int wishplatCount = wishplatService.getCountOfWishlist(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("no of wishbooks", 200, wishplatCount));
	}
}
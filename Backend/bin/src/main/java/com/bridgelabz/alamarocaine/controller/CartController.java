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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.alamarocaine.dto.CartDto;
import com.bridgelabz.alamarocaine.entity.CartItem;
import com.bridgelabz.alamarocaine.response.Response;
import com.bridgelabz.alamarocaine.service.ICartService;

@RestController
@CrossOrigin
public class CartController {

	@Autowired
	private ICartService cartService;

	@PostMapping("alamarocaine/v3/cart/addplatCart/{platId}")
	public ResponseEntity<Response> addToCartPlats(@RequestHeader String token, @PathVariable long platId)
			throws Exception {
		List<CartItem> cartItem = cartService.addPlattoCart(token, platId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("plat added to cart", 200, cartItem));
	}

	@GetMapping("alamarocaine/v3/cart/getcartplats")
	public ResponseEntity<Response> getPlatsfromCart(@RequestHeader(name = "token") String token) throws Exception {
		List<CartItem> cartdetails = cartService.getPlatsfromCart(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Response("plats fetched from cart", 200, cartdetails));
	}

	@DeleteMapping("alamarocaine/v3/cart/removeCartPlats/{platId}")
	public ResponseEntity<Response> removePlatsToCart(@RequestHeader(name = "token") String token,
			@PathVariable Long platId) throws Exception {
		System.out.println("jjjjjjjjjjj" + token + platId);
		boolean cartdetails = cartService.removePlatsFromCart(token, platId);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Response("plat removed from cart", 200, cartdetails));
	}

	@GetMapping("alamarocaine/v3/cart/platCount")
	public ResponseEntity<Response> getPlatsCount(@RequestHeader(name = "token") String token) throws Exception {
		int cartdetails = cartService.getCountOfPlats(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Response("Count of to plat in cart", 200, cartdetails));
	}

	@PutMapping("alamarocaine/v3/cart/increaseplatsquantity")
	public ResponseEntity<Response> increasePlatsQuantity(@RequestHeader String token, @RequestParam Long platId,
			@RequestBody CartDto platQuantityDetails) throws Exception {
		CartItem cartdetails = cartService.IncreasePlatsQuantityInCart(token, platId, platQuantityDetails);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Response("increased 1 quantity of plat ", 200, cartdetails));

	}

	@PutMapping("alamarocaine/v3/cart/decreaseQuantityPrice")
	public ResponseEntity<Response> descreasePlatsQuantity(@RequestHeader(name = "token") String token,
			@RequestParam("platId") Long platId, @RequestBody CartDto platQuantityDetails) throws Exception {
		CartItem cartdetails = cartService.descreasePlatsQuantityInCart(token, platId, platQuantityDetails);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Response("decreased 1 quantity of plat", 200, cartdetails));
	}
}

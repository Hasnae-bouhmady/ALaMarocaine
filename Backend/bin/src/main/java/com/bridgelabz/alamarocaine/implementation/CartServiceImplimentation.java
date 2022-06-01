package com.bridgelabz.alamarocaine.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.alamarocaine.dto.CartDto;
import com.bridgelabz.alamarocaine.entity.CartItem;
import com.bridgelabz.alamarocaine.entity.Plat;
import com.bridgelabz.alamarocaine.entity.Quantity;
import com.bridgelabz.alamarocaine.entity.Users;
import com.bridgelabz.alamarocaine.entity.WishlistPlat;
import com.bridgelabz.alamarocaine.exception.UserException;
import com.bridgelabz.alamarocaine.repository.PlatImple;
import com.bridgelabz.alamarocaine.repository.QuantityRepository;
import com.bridgelabz.alamarocaine.repository.UserRepository;
import com.bridgelabz.alamarocaine.service.ICartService;
import com.bridgelabz.alamarocaine.service.IWishlistService;
import com.bridgelabz.alamarocaine.util.JwtGenerator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartServiceImplimentation implements ICartService {
	@Autowired
	private JwtGenerator generate;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PlatImple platRepository;
	@Autowired
	private IWishlistService wishService;
	@Autowired
	private QuantityRepository quantityRepository;
	Users user = new Users();

	@Transactional
	@Override
	public List<CartItem> addPlattoCart(String token, long platId) {

		Long id;

		id = (long) generate.parseJWT(token);

		Users user = userRepository.findById(id).orElse(null);

		Plat plat = platRepository.findById(platId).get();
		if (plat != null) {
			// if the book present in wishlist and book number is not equal to zero
			Long l = plat.getNoOfPlats();
			int i = l.intValue();
			// log.info("-------hitting or not------------------------" + i);
			if (i > 0) {
				// log.info("-------hitting or not------------------------" + i);
				List<WishlistPlat> wishplat = user.getWishlistPlat();
				for (WishlistPlat wishplats : wishplat) {

					boolean b = wishplats.getPlatsList().contains(plat);
					if (b == true) {
						wishService.removeWishPlat(token, plat.getPlatId());
					}

				} // if book present in the wishbook

				List<Plat> plats = null;
				for (CartItem d : user.getCartPlats()) {
					plats = d.getPlatsList();
				}

				if (plats == null) {
					Users userdetails = this.cartplats(plat, user);
					return userRepository.save(userdetails).getCartPlats();
				}
				/**
				 * Checking whether book is already present r not
				 */

				Optional<Plat> cartplat = plats.stream().filter(t -> t.getPlatId() == platId).findFirst();

				if (cartplat.isPresent()) {
					throw null;
				} else {
					Users userdetails = this.cartplats(plat, user);
					return userRepository.save(userdetails).getCartPlats();
				}

			} // i==0

			throw new UserException("Out of stock u cannot add to cart");
		} // book
		return null;

	}

	public Users cartplats(Plat plat, Users user) {
		long quantity = 1;
		CartItem cart = new CartItem();
		Quantity qunatityofplat = new Quantity();
		ArrayList<Plat> platlist = new ArrayList<>();
		platlist.add(plat);
		cart.setCreatedTime(LocalDateTime.now());
		cart.setPlatsList(platlist);
		ArrayList<Quantity> quantitydetails = new ArrayList<Quantity>();
		qunatityofplat.setQuantityOfPlat(quantity);

		qunatityofplat.setTotalprice(plat.getPrice());

		quantitydetails.add(qunatityofplat);
		cart.setQuantityOfPlat(quantitydetails);
		user.getCartPlats().add(cart);
		return user;
	}

	@Transactional
	@Override
	public List<CartItem> getPlatsfromCart(String token) {
		Long id = (long) generate.parseJWT(token);
		Users user = userRepository.findById(id).get();
		if (user != null) {
			List<CartItem> cartItem = new ArrayList<>();
			for (CartItem cartPlats : user.getCartPlats()) {
				if (!(cartPlats.getPlatsList().isEmpty())) {
					cartItem.add(cartPlats);
				}
			}
			return cartItem;
		} // user.

		return null;
	}

	@Transactional
	@Override
	public boolean removePlatsFromCart(String token, Long platId) {
		Long id;

		id = (long) generate.parseJWT(token);
		Users user = userRepository.findById(id).get();
		if (user != null) {
			Plat plat = platRepository.findById(platId).get();
			if (plat != null) {
				Quantity quantity = quantityRepository.findById(id).orElseThrow(null);
				for (CartItem cartt : user.getCartPlats()) {
					boolean exitsPlatInCart = cartt.getPlatsList().stream()
							.noneMatch(plats -> plats.getPlatId().equals(platId));
					if (!exitsPlatInCart) {
						userRepository.save(user);
						cartt.getQuantityOfPlat().remove(quantity);
						cartt.getPlatsList().remove(plat);
						cartt.getQuantityOfPlat().clear();
						boolean users = userRepository.save(user).getCartPlats() != null ? true : false;
						if (user != null) {
							return users;
						}
					}

				}
			} // book
				// .book....exception here....
		} // user
		return false;

	}

	@Transactional
	@Override
	public int getCountOfPlats(String token) {
		Long id;

		id = (long) generate.parseJWT(token);
		int countOfPlats = 0;
		Users user = userRepository.findById(id).get();
		if (user != null) {
			List<CartItem> cartPlats = user.getCartPlats();
			for (CartItem cart : cartPlats) {
				if (!cart.getPlatsList().isEmpty()) {
					countOfPlats++;
				}
			}
			return countOfPlats;
		} // user
			// ....write userwxception

		return 0;
	}

	@Transactional
	@Override
	public CartItem IncreasePlatsQuantityInCart(String token, Long platId, CartDto platQuantityDetails) {
		Long id;
		id = (long) generate.parseJWT(token);

		Long quantityId = platQuantityDetails.getQuantityId();
		Long quantity = platQuantityDetails.getQuantityOfPlat();
		Users user = userRepository.findById(id).get();
		if (user != null) {
			Plat plat = platRepository.findById(platId).get();
			if (plat != null) {

				double totalprice = plat.getPrice() * (quantity + 1);
				boolean notExist = false;
				for (CartItem cartt : user.getCartPlats()) {
					if (!cartt.getPlatsList().isEmpty()) {
						notExist = cartt.getPlatsList().stream().noneMatch(plats -> plats.getPlatId().equals(platId));

						if (!notExist) {

//						

							Quantity quantityDetails = quantityRepository.findById(quantityId).orElseThrow(null);
							quantityDetails.setQuantityOfPlat(quantity + 1);
							quantityDetails.setTotalprice(totalprice);
							if (quantityDetails.getQuantityOfPlat() <= plat.getNoOfPlats()) {
								quantityRepository.save(quantityDetails);
								return cartt;
							}
							throw new UserException("there is no enough quantity of plat");

						}

					} // cart
				}

			} // book

		} // user

		return null;
	}

	@Transactional
	@Override
	public CartItem descreasePlatsQuantityInCart(String token, Long platId, CartDto platQuantityDetails) {
		Long id;

		id = (long) generate.parseJWT(token);
		Long quantityId = platQuantityDetails.getQuantityId();
		Long quantity = platQuantityDetails.getQuantityOfPlat();

		Users user = userRepository.findById(id).get();
		if (user != null) {
			Plat plat = platRepository.findById(platId).get();
			if (plat != null) {
				double totalprice = plat.getPrice() * (quantity - 1);
				boolean notExist = false;
				for (CartItem cartt : user.getCartPlats()) {
					if (!cartt.getPlatsList().isEmpty()) {
						notExist = cartt.getPlatsList().stream().noneMatch(plats -> plats.getPlatId().equals(platId));
						if (!notExist) {

							Quantity quantityDetails = quantityRepository.findById(quantityId).orElseThrow(null);
							quantityDetails.setQuantityOfPlat(quantity - 1);
							quantityDetails.setTotalprice(totalprice);
							Long l = quantityDetails.getQuantityOfPlat();
							int i = l.intValue();
							if (i >= 0) {
								if (i == 0) {
									removePlatsFromCart(token, platId);
								}
								quantityRepository.save(quantityDetails);
								return cartt;
							}
							throw new UserException("invalid Quantity");
						}

					}
				}
			} // book

		} // user
		return null;

	}

}
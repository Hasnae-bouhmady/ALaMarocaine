package com.bridgelabz.alamarocaine.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.alamarocaine.entity.Plat;
import com.bridgelabz.alamarocaine.entity.Users;
import com.bridgelabz.alamarocaine.entity.WishlistPlat;
import com.bridgelabz.alamarocaine.repository.PlatImple;
import com.bridgelabz.alamarocaine.repository.UserRepository;
import com.bridgelabz.alamarocaine.service.IWishlistService;
import com.bridgelabz.alamarocaine.util.JwtGenerator;

@Service
public class WishlistImplementation implements IWishlistService {
	@Autowired
	private JwtGenerator generate;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PlatImple platRepository;

	Users user = new Users();
	private boolean notifyWishplats;

	public boolean isNotifyWishplats() {
		return notifyWishplats;
	}

	public void setNotifyWishplats(boolean notifyWishplats) {
		this.notifyWishplats = notifyWishplats;
	}

	@Override
	@Transactional
	public List<WishlistPlat> addwishPlat(String token, long platId) {

		Long id = generate.parseJWT(token);
		System.out.println("-------------   hitting 1");
		Users user = userRepository.findById(id).get();
		if (user != null) {
			System.out.println("-------------   hitting user ");
			Plat plat = platRepository.findById(platId).get();
			if (plat != null) {
				List<Plat> plats = null;
				for (WishlistPlat d : user.getWishlistPlat()) {
					plats = d.getPlatsList();
				}
				if (plats == null) {
					Users userdetails = this.wishplats(plat, user);
					return userRepository.save(userdetails).getWishlistPlat();
				}

				Optional<Plat> wishplat = plats.stream().filter(t -> t.getPlatId() == platId).findFirst();
				if (wishplat.isPresent()) {
					throw null;
				} else {
					Users userdetails = this.wishplats(plat, user);
					return userRepository.save(userdetails).getWishlistPlat();
				}

			} // book
		} // user

		// write here exception........
		return null;

	}

	public Users wishplats(Plat plat, Users user) {

		WishlistPlat wishplat = new WishlistPlat();
		ArrayList<Plat> platlist = new ArrayList<>();
		platlist.add(plat);
		wishplat.setWishlistTime(LocalDateTime.now());
		wishplat.setPlatsList(platlist);
		user.getWishlistPlat().add(wishplat);
		return user;

	}

	@Override
	@Transactional
	public List<WishlistPlat> getWishlistPlats(String token) {
		Long id;

		id = (long) generate.parseJWT(token);
		Users user = userRepository.findById(id).get();
		if (user != null) {
			List<WishlistPlat> wishPlats = user.getWishlistPlat();
			List<WishlistPlat> platsinWish = new ArrayList<>();
			for (WishlistPlat plat : wishPlats) {
				if (!(plat.getPlatsList().isEmpty())) {
					platsinWish.add(plat);

				}
				Plat platstackdeatils;
				for (Plat platstack : plat.getPlatsList()) {
					Long l = platstack.getNoOfPlats();
					int i = l.intValue();
					if (i == 0) {
						platstackdeatils = platstack;
						setNotifyWishplats(false);
					}
				}

			}

			return platsinWish;
		}
		// write here exception........
		return null;
	}

	@Override
	@Transactional
	public boolean removeWishPlat(String token, Long platId) {

		Long id;
		id = (long) generate.parseJWT(token);
		Users user = userRepository.findById(id).get();
		if (user != null) {
			Plat plat = platRepository.findById(platId).get();
			if (plat != null) {

				for (WishlistPlat wishlist : user.getWishlistPlat()) {
					boolean exitsInWishlist = wishlist.getPlatsList().stream()
							.noneMatch(plats -> plats.getPlatId().equals(platId));
					if (!exitsInWishlist) {
						userRepository.save(user);
						wishlist.getPlatsList().remove(plat);
						wishlist.getPlatsList().clear();
						boolean users = userRepository.save(user).getWishlistPlat() != null ? true : false;
						if (user != null) {
							return users;
						}
					}
				}
			} // book
				// book exception

		} // user
			// exception user

		return false;
	}

	@Override
	@Transactional
	public int getCountOfWishlist(String token) {
		Long id;
		id = (long) generate.parseJWT(token);
		int countOfWishList = 0;
		Users user = userRepository.findById(id).get();
		if (user != null) {
			List<WishlistPlat> wishlist = user.getWishlistPlat();
			for (WishlistPlat wishplat : wishlist) {
				if (!wishplat.getPlatsList().isEmpty()) {
					countOfWishList++;
				}
			}
			return countOfWishList;
		}
		// write here exception...................

		return 0;
	}

}
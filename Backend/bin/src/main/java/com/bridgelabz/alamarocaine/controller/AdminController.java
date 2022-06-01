package com.bridgelabz.alamarocaine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.alamarocaine.entity.Plat;
import com.bridgelabz.alamarocaine.repository.ChefInterface;
import com.bridgelabz.alamarocaine.repository.PlatInterface;
import com.bridgelabz.alamarocaine.response.PlatResponse;
import com.bridgelabz.alamarocaine.service.IAdminService;

@RestController
@CrossOrigin("*")
public class AdminController {

	@Autowired
	private IAdminService adminService;

	@Autowired
	PlatInterface platRepo;

	@Autowired
	ChefInterface chefRepo;

	@PutMapping("admin/update/{platId}")
	public ResponseEntity<PlatResponse> updatePlatStatus(@PathVariable long platId, @RequestParam String status,
			@RequestHeader String token) {
		if (adminService.verifyPlat(platId, status, token)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new PlatResponse("Seller plat status updated by the admin", HttpStatus.ACCEPTED));
		}
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new PlatResponse(406, "Bad Response"));
	}

	/**
	 * h @PutMapping("admin/update/{cheftd}") public ResponseEntity<ChefResponse>
	 * updatChefStatus(@PathVariable long chefId, @RequestParam String status,
	 * 
	 * @RequestHeader String token) { if (adminService.verifyPlat(chefId, status,
	 *                token)) { return ResponseEntity.status(HttpStatus.OK)
	 *                .body(new ChefResponse("Seller chef status updated by the
	 *                admin", HttpStatus.ACCEPTED)); } return
	 *                ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new
	 *                ChefResponse(406, "Bad Response")); }
	 * 
	 **/

	@GetMapping("admin/plats")
	public ResponseEntity<PlatResponse> getAllPlatsByStatus(@RequestParam String status) {
		List<Plat> plats = adminService.getPlatsByStatus(status);

		return ResponseEntity.status(HttpStatus.OK).body(new PlatResponse(status + " Plats ", plats));

	}

	/**
	 * h @GetMapping("admin/chefs") public ResponseEntity<ChefResponse>
	 * getAllChefsByStatus(@RequestParam String status) { List<Chef> chefs =
	 * adminService.getChefsByStatus(status);
	 * 
	 * return ResponseEntity.status(HttpStatus.OK).body(new ChefResponse(status + "
	 * Chefs ", chefs));
	 * 
	 * }
	 * 
	 **/

}
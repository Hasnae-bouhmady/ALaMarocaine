package com.bridgelabz.alamarocaine.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.alamarocaine.dto.ChefDto;
import com.bridgelabz.alamarocaine.dto.EditChefDto;
import com.bridgelabz.alamarocaine.dto.EditPlatDto;
import com.bridgelabz.alamarocaine.dto.PlatDto;
import com.bridgelabz.alamarocaine.dto.RatingReviewDTO;
import com.bridgelabz.alamarocaine.entity.Chef;
import com.bridgelabz.alamarocaine.entity.Plat;
import com.bridgelabz.alamarocaine.entity.ReviewAndRating;
import com.bridgelabz.alamarocaine.response.ChefResponse;
import com.bridgelabz.alamarocaine.response.PlatResponse;
import com.bridgelabz.alamarocaine.service.IChefService;
import com.bridgelabz.alamarocaine.service.IPlatService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
public class ALaMarocaineController {

	/************* h Partie plats **************/

	@Autowired
	IPlatService platservice;

	@PostMapping("plats/{imageName}")
	public ResponseEntity<PlatResponse> addPlat(@PathVariable String imageName, @RequestBody PlatDto information,
			@RequestHeader("token") String token) {

		boolean res = platservice.addPlats(imageName, information, token);
		if (res)
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new PlatResponse(200, "Le plat ajouté avec succés..."));
		else
			return ResponseEntity.status(HttpStatus.CREATED).body(new PlatResponse(400, "The Book details not added "));
	}

	@GetMapping("plats/")
	public ResponseEntity<PlatResponse> getPlats(@RequestHeader("token") String token) {
		List<Plat> plats = platservice.getPlatInfo(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new PlatResponse("The Book details are", plats));
	}

	/**
	 * This controller is for getting 12 approval books per page! it can search book
	 * based on there autherName it can sort the book by anything like price,
	 * book_name, book_id etc, it can order the book both asc and desc order default
	 * will be desc order it can return the book based on there passing url
	 * paramater
	 * 
	 * @param searchByPlatName example (" ", book name, raju, etc)
	 * @param page             Example (" ", 1,2,3,4.........)
	 * @param sortBy           example (" ", book_id, price, created_date_and_time
	 *                         etc)
	 * @param order            (" ", asc,desc,)
	 * @return 12 books and number of page and everything
	 */

	@GetMapping("plats/approved")
	public ResponseEntity<PlatResponse> getAllApproved(@RequestParam Optional<String> searchByPlatName,
			@RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy,
			@RequestParam Optional<String> order) {
		Page<Plat> plats = platservice.getPlatAproval(searchByPlatName, page, sortBy, order);
		if (plats != null)
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new PlatResponse("The Approved Book details are", plats));
		else
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new PlatResponse(400, "No Approved Books available"));
	}

	@GetMapping(value = "plats/getplat/{platId}")
	public ResponseEntity<PlatResponse> getPlatbyId(@PathVariable("platId") Long platId) {
		Plat info = platservice.getPlatbyId(platId);
		return ResponseEntity.status(HttpStatus.OK).body(new PlatResponse("The plat is", info));
	}

	@PutMapping("plats/{platId}")
	public ResponseEntity<PlatResponse> editPlat(@PathVariable("platId") long platId,
			@RequestBody EditPlatDto information, @RequestHeader("token") String token) {
		boolean res = platservice.editPlat(platId, information, token);
		if (res)
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new PlatResponse(200, "le plat a été modifié avec succès"));
		return null;
	}

	@DeleteMapping("plats/{platId}")
	public ResponseEntity<PlatResponse> deletePlat(@PathVariable long platId, @RequestHeader("token") String token) {
		boolean res = platservice.deletePlat(platId, token);
		if (res)
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new PlatResponse(202, "Le plat est supprimé"));
		return null;
	}

	// A SUPPRIMER
	@PutMapping("plats/{platId}/{status}")
	public ResponseEntity<PlatResponse> editPlatStatus(@PathVariable long platId, @PathVariable String status,
			@RequestHeader("token") String token) {
		boolean res = platservice.editPlatStatus(platId, status, token);
		if (res)
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new PlatResponse(202, "The Book Status is changed sucessfully.."));
		else
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new PlatResponse(400, "The Book Status is not updated.."));
	}

	/// A SUPPRIMER
	@GetMapping("plats/onHoldPlats")
	public ResponseEntity<PlatResponse> getAllOnHoldPlats(@RequestHeader("token") String token) {
		List<Plat> plats = platservice.getAllOnHoldPlats(token);
		if (plats != null)
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new PlatResponse("The Approved & OnHold Book details are", plats));
		else
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new PlatResponse(400, "No Approved & OnHold Books available"));
	}

	// A SUPPRIMER
	@GetMapping("plats/rejectedPlats")
	public ResponseEntity<PlatResponse> getAllRejectedPlats(@RequestHeader("token") String token) {
		List<Plat> plats = platservice.getAllRejectedPlats(token);
		if (plats != null)
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new PlatResponse("The Rejected plat details are", plats));
		else
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new PlatResponse(400, "No Rejected plats available"));
	}

	@ApiOperation(value = "get all rating and reviews of the plat ")
	@GetMapping("plats/getratereviews")
	public ResponseEntity<PlatResponse> getPlatRatingAndReview(@RequestParam Long platId) {
		List<ReviewAndRating> rr = platservice.getRatingsOfPlat(platId);
		if (rr != null)
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new PlatResponse("Ratings and review", rr));
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new PlatResponse("Ratings and review not found", rr));
	}

	@ApiOperation(value = "Get verified plats Count")
	@GetMapping("plats/count")
	public ResponseEntity<PlatResponse> getPlatsCount() {
		int platcount = platservice.getPlatsCount();
		return ResponseEntity.status(HttpStatus.OK).body(new PlatResponse("Ratings and review", platcount));

	}

	@ApiOperation(value = "Write Review of the plat")
	@PutMapping("plats/ratingreview")
	public ResponseEntity<PlatResponse> writeReview(@RequestBody RatingReviewDTO rrDto,
			@RequestHeader(name = "token") String token, @RequestParam Long platId) {
		if (platservice.writeReviewAndRating(token, rrDto, platId))
			return ResponseEntity.status(HttpStatus.OK).body(new PlatResponse("Thank you..for your review", 200));
		else
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
					.body(new PlatResponse("You are already given rate", 208));

	}

	@ApiOperation(value = "Average rating of the plat")
	@GetMapping("plats/avgrate")
	public ResponseEntity<PlatResponse> avgRatingOfPlat(@RequestParam long platId) {
		double rate = platservice.avgRatingOfPlat(platId);
		if (rate > 0.0)
			return ResponseEntity.status(HttpStatus.OK).body(new PlatResponse("Avg rate", rate));
		else

			return ResponseEntity.status(HttpStatus.OK).body(new PlatResponse("Avg rate", 0));

	}

	@ApiOperation(value = "Plats sorted by rating")
	@GetMapping("plats/sortbyrate")
	public ResponseEntity<PlatResponse> sortPlatByRate() {
		List<Plat> plats = platservice.sortPlatByRate();
		if (plats != null)
			return ResponseEntity.status(HttpStatus.OK).body(new PlatResponse("plats fetched", plats));
		else
			return ResponseEntity.status(HttpStatus.OK).body(new PlatResponse("plats not fetched", plats));
	}

	@PostMapping("plats/platimage/{platId}")
	public ResponseEntity<PlatResponse> uploadImageP(@RequestParam("imageFile") MultipartFile file,
			@RequestHeader String token, @PathVariable long platId) {
		String imageName = file.getOriginalFilename();
		boolean res = platservice.uploadPlatImage(platId, imageName, token);
		if (res)
			return ResponseEntity.status(HttpStatus.OK).body(new PlatResponse(202, "Image Uploaded Succesfully"));
		else
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new PlatResponse(203, "error"));

	}

	/*** h Partie chefs *****/

	@Autowired
	IChefService chefservice;

	@PostMapping("chefs/{imageName}")
	public ResponseEntity<ChefResponse> addChef(@PathVariable String imageName, @RequestBody ChefDto information,
			@RequestHeader("token") String token) {

		boolean res = chefservice.addChefs(imageName, information, token);
		if (res)
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ChefResponse(200, "Le chef ajouté avec succés..."));
		else
			return ResponseEntity.status(HttpStatus.CREATED).body(new ChefResponse(400, "The Book details not added "));
	}

	@GetMapping("chefs/")
	public ResponseEntity<ChefResponse> getChefs(@RequestHeader("token") String token) {
		List<Chef> chefs = chefservice.getChefInfo(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ChefResponse("The Book details are", chefs));
	}

	/**
	 * This controller is for getting 12 approval books per page! it can search book
	 * based on there autherName it can sort the book by anything like price,
	 * book_name, book_id etc, it can order the book both asc and desc order default
	 * will be desc order it can return the book based on there passing url
	 * paramater
	 * 
	 * @param searchByPlatName example (" ", book name, raju, etc)
	 * @param page             Example (" ", 1,2,3,4.........)
	 * @param sortBy           example (" ", book_id, price, created_date_and_time
	 *                         etc)
	 * @param order            (" ", asc,desc,)
	 * @return 12 books and number of page and everything
	 */

	/**
	 * h @GetMapping("chefs/approved") public ResponseEntity<PlatResponse>
	 * getAllApproved(@RequestParam Optional<String> searchByPlatName,
	 * 
	 * @RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy,
	 * @RequestParam Optional<String> order) { Page<Plat> plats =
	 *               platservice.getPlatAproval(searchByPlatName, page, sortBy,
	 *               order); if (plats != null) return
	 *               ResponseEntity.status(HttpStatus.ACCEPTED) .body(new
	 *               PlatResponse("The Approved Book details are", plats)); else
	 *               return ResponseEntity.status(HttpStatus.ACCEPTED) .body(new
	 *               PlatResponse(400, "No Approved Books available")); }
	 **/

	@GetMapping(value = "chefs/getchef/{chefId}")
	public ResponseEntity<ChefResponse> getChefbyId(@PathVariable("chefId") Long chefId) {
		Chef info = chefservice.getChefbyId(chefId);
		return ResponseEntity.status(HttpStatus.OK).body(new ChefResponse("The chef is", info));
	}

	@PutMapping("chefs/{chefId}")
	public ResponseEntity<ChefResponse> editChef(@PathVariable("chefId") long chefId,
			@RequestBody EditChefDto information, @RequestHeader("token") String token) {
		boolean res = chefservice.editChef(chefId, information, token);
		if (res)
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ChefResponse(200, "le chef a été modifié avec succès"));
		return null;
	}

	@DeleteMapping("chefs/{chefId}")
	public ResponseEntity<ChefResponse> deleteChef(@PathVariable long chefId, @RequestHeader("token") String token) {
		boolean res = chefservice.deleteChef(chefId, token);
		if (res)
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ChefResponse(202, "Le chef est supprimé"));
		return null;
	}

	/**
	 * h // A SUPPRIMER @PutMapping("plats/{platId}/{status}") public
	 * ResponseEntity<PlatResponse> editPlatStatus(@PathVariable long
	 * platId, @PathVariable String status, @RequestHeader("token") String token) {
	 * boolean res = platservice.editPlatStatus(platId, status, token); if (res)
	 * return ResponseEntity.status(HttpStatus.ACCEPTED) .body(new PlatResponse(202,
	 * "The Book Status is changed sucessfully..")); else return
	 * ResponseEntity.status(HttpStatus.ACCEPTED) .body(new PlatResponse(400, "The
	 * Book Status is not updated..")); }
	 **/

	/**
	 * h /// A SUPPRIMER @GetMapping("plats/onHoldPlats") public
	 * ResponseEntity<PlatResponse> getAllOnHoldPlats(@RequestHeader("token") String
	 * token) { List<Plat> plats = platservice.getAllOnHoldPlats(token); if (plats
	 * != null) return ResponseEntity.status(HttpStatus.ACCEPTED) .body(new
	 * PlatResponse("The Approved & OnHold Book details are", plats)); else return
	 * ResponseEntity.status(HttpStatus.ACCEPTED) .body(new PlatResponse(400, "No
	 * Approved & OnHold Books available")); }
	 **/

	/**
	 * h // A SUPPRIMER @GetMapping("plats/rejectedPlats") public
	 * ResponseEntity<PlatResponse> getAllRejectedPlats(@RequestHeader("token")
	 * String token) { List<Plat> plats = platservice.getAllRejectedPlats(token); if
	 * (plats != null) return ResponseEntity.status(HttpStatus.ACCEPTED) .body(new
	 * PlatResponse("The Rejected plat details are", plats)); else return
	 * ResponseEntity.status(HttpStatus.ACCEPTED) .body(new PlatResponse(400, "No
	 * Rejected plats available")); }
	 * 
	 **/

	/**
	 * h
	 * 
	 * @ApiOperation(value = "get all rating and reviews of the plat
	 *                     ") @GetMapping("plats/getratereviews") public
	 *                     ResponseEntity<PlatResponse>
	 *                     getPlatRatingAndReview(@RequestParam Long platId) {
	 *                     List<ReviewAndRating> rr =
	 *                     platservice.getRatingsOfPlat(platId); if (rr != null)
	 *                     return
	 *                     ResponseEntity.status(HttpStatus.ACCEPTED).body(new
	 *                     PlatResponse("Ratings and review", rr)); else return
	 *                     ResponseEntity.status(HttpStatus.NOT_FOUND) .body(new
	 *                     PlatResponse("Ratings and review not found", rr)); }
	 * 
	 * 
	 **/

	/**
	 * h
	 * 
	 * @ApiOperation(value = "Get verified plats Count") @GetMapping("plats/count")
	 *                     public ResponseEntity<PlatResponse> getPlatsCount() { int
	 *                     platcount = platservice.getPlatsCount(); return
	 *                     ResponseEntity.status(HttpStatus.OK).body(new
	 *                     PlatResponse("Ratings and review", platcount));
	 * 
	 *                     }
	 * 
	 **/

	/**
	 * h
	 * 
	 * @ApiOperation(value = "Write Review of the
	 *                     plat") @PutMapping("plats/ratingreview") public
	 *                     ResponseEntity<PlatResponse> writeReview(@RequestBody
	 *                     RatingReviewDTO rrDto,
	 * @RequestHeader(name = "token") String token, @RequestParam Long platId) { if
	 *                     (platservice.writeReviewAndRating(token, rrDto, platId))
	 *                     return ResponseEntity.status(HttpStatus.OK).body(new
	 *                     PlatResponse("Thank you..for your review", 200)); else
	 *                     return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
	 *                     .body(new PlatResponse("You are already given rate",
	 *                     208));
	 * 
	 *                     }
	 * 
	 * 
	 **/

	/**
	 * h
	 * 
	 * @ApiOperation(value = "Average rating of the
	 *                     plat") @GetMapping("plats/avgrate") public
	 *                     ResponseEntity<PlatResponse>
	 *                     avgRatingOfPlat(@RequestParam long platId) { double rate
	 *                     = platservice.avgRatingOfPlat(platId); if (rate > 0.0)
	 *                     return ResponseEntity.status(HttpStatus.OK).body(new
	 *                     PlatResponse("Avg rate", rate)); else
	 * 
	 *                     return ResponseEntity.status(HttpStatus.OK).body(new
	 *                     PlatResponse("Avg rate", 0));
	 * 
	 *                     }
	 **/

	/**
	 * 
	 * @ApiOperation(value = "Plats sorted by
	 *                     rating") @GetMapping("plats/sortbyrate") public
	 *                     ResponseEntity<PlatResponse> sortPlatByRate() {
	 *                     List<Plat> plats = platservice.sortPlatByRate(); if
	 *                     (plats != null) return
	 *                     ResponseEntity.status(HttpStatus.OK).body(new
	 *                     PlatResponse("plats fetched", plats)); else return
	 *                     ResponseEntity.status(HttpStatus.OK).body(new
	 *                     PlatResponse("plats not fetched", plats)); }
	 * 
	 ***/

	@PostMapping("chefs/chefimage/{chefId}")
	public ResponseEntity<ChefResponse> uploadImage(@RequestParam("imageFile") MultipartFile file,
			@RequestHeader String token, @PathVariable long chefId) {
		String imageName = file.getOriginalFilename();
		boolean res = chefservice.uploadChefImage(chefId, imageName, token);
		if (res)
			return ResponseEntity.status(HttpStatus.OK).body(new ChefResponse(202, "Image Uploaded Succesfully"));
		else
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ChefResponse(203, "error"));
	}

}

package com.bridgelabz.alamarocaine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.alamarocaine.dto.UserDto;
import com.bridgelabz.alamarocaine.entity.Users;
import com.bridgelabz.alamarocaine.exception.UserException;
import com.bridgelabz.alamarocaine.request.LoginInformation;
import com.bridgelabz.alamarocaine.request.PasswordReset;
import com.bridgelabz.alamarocaine.request.PasswordUpdate;
import com.bridgelabz.alamarocaine.response.Response;
import com.bridgelabz.alamarocaine.response.UsersDetailRes;
import com.bridgelabz.alamarocaine.service.UserServices;
import com.bridgelabz.alamarocaine.util.JwtGenerator;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private UserServices service;

	@Autowired
	private JwtGenerator generate;

	@PostMapping("/registration")
	@ResponseBody
	public ResponseEntity<Response> registration(@RequestBody UserDto information) {
		System.out.println("user info" + information.toString());
		boolean result = service.register(information);
		if (result) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new Response("inscription r�ussi", 200, information));
		} else {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
					.body(new Response("Utilisateur existe d�ja !", 400, information));
		}
	}

	@PostMapping("/user/login")
	public ResponseEntity<UsersDetailRes> login(@RequestBody LoginInformation information) {

		Users users = service.login(information);
		if (users != null) {
			String token = generate.jwtToken(users.getUserId());
			return ResponseEntity.status(HttpStatus.ACCEPTED).header("connexion r�ussie", information.getEmail())
					.body(new UsersDetailRes(token, 200, users));
		} else {
			throw new UserException(" les informations d'identification invalides");
		}
	}

	/**
	 * This is for the user verify.......
	 * 
	 * @param token
	 * @return response as success and fail
	 * @throws Exception
	 */
	@GetMapping("/user/verify/{token}")
	public ResponseEntity<Response> userVerification(@PathVariable("token") String token) throws Exception {
		System.out.println("jeton de v�rification" + token);
		boolean update = service.verify(token);
		if (update) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("v�rifi�", 200));
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("non v�rifi�", 400));
		}
	}

	/**
	 * This is used for the get one user based on there token
	 * 
	 * @param token
	 * @return response
	 */
	@PostMapping("user/forgotpassword")
	public ResponseEntity<Response> forgogPassword(@RequestBody PasswordReset passwordReset) {

		boolean result = service.isUserExist(passwordReset.getEmail());
		if (result) {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new Response("V�rifiez votre boite mail : e-mail envoy�", 200));
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("l'email saisie n'existe pas!", 400));
		}
	}

	@PutMapping("user/update/{token}")
	public ResponseEntity<Response> update(@PathVariable("token") String token, @RequestBody PasswordUpdate update) {
		System.out.println("contr�leur interne  " + token);
		boolean result = service.update(update, token);
		if (result) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("", 200));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Response("le mot de passe ne correspond pas", 401));
		}

	}

	@GetMapping("user/getOneUser")
	public ResponseEntity<Response> getOneUsers(@RequestHeader("token") String token) {
		Users user = service.getSingleUser(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("user is", 200, user));
	}

}
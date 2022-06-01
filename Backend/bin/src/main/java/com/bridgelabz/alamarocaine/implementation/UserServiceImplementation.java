/**
 * 
 */
package com.bridgelabz.alamarocaine.implementation;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.alamarocaine.dto.UserDto;
import com.bridgelabz.alamarocaine.entity.Users;
import com.bridgelabz.alamarocaine.exception.UserException;
import com.bridgelabz.alamarocaine.repository.IUserRepository;
import com.bridgelabz.alamarocaine.request.LoginInformation;
import com.bridgelabz.alamarocaine.request.PasswordUpdate;
import com.bridgelabz.alamarocaine.response.EmailData;
import com.bridgelabz.alamarocaine.response.MailResponse;
import com.bridgelabz.alamarocaine.service.UserServices;
import com.bridgelabz.alamarocaine.util.EmailProviderService;
import com.bridgelabz.alamarocaine.util.JwtGenerator;
import com.bridgelabz.alamarocaine.util.MailServiceProvider;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserServiceImplementation implements UserServices {
	private Users users = new Users();
	@Autowired
	private IUserRepository repository;

	@Autowired
	private BCryptPasswordEncoder encryption;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JwtGenerator generate;

	@Autowired
	private MailResponse response;

	@Autowired
	private EmailProviderService em;
	@Autowired
	private EmailData emailData;

	@Override
	@Transactional
	public boolean register(UserDto information) {
		Users user = repository.getUser(information.getEmail());
		if (user == null) {
			users = modelMapper.map(information, Users.class);
			users.setCreatedDate(LocalDateTime.now());
			String epassword = encryption.encode(information.getPassword());
			// setting the some extra information and encrypting the password
			users.setPassword(epassword);
			System.out.println("password is" + epassword);
			users.setVerified(false);
			// calling the save method
			users = repository.save(users);
			String mailResponse = "http://localhost:8020/user/verify/" + generate.jwtToken(users.getUserId());

			emailData.setEmail(users.getEmail());
			emailData.setSubject("votre inscription est r�ussie");
			emailData.setBody(mailResponse);
			em.sendMail(emailData.getEmail(), emailData.getSubject(), emailData.getBody());
			System.out.println(mailResponse);
			return true;
		} else {
			throw new UserException("l'utilisateur existe déja avec le même email");
		}
	}

	@Override
	public Users login(LoginInformation information) {
		Users user = repository.getUser(information.getEmail());
		if (user != null) {
			String userRole = information.getRole();
			String fetchRole = user.getRole();
			if (fetchRole.equals(userRole)) {
				Users userInfo = verifyPassword(user, information);
				// log.info("you logged in as " + userRole);
				return userInfo;
			} else if (fetchRole.equals(userRole)) {
				Users userInfo = verifyPassword(user, information);
				// log.info("you logged in as " + userRole);
				return userInfo;
			} else if (fetchRole.equals(userRole)) {
				Users userInfo = verifyPassword(user, information);
				// log.info("you logged in as " + userRole);
				return userInfo;
			} else {
				throw new UserException("Authorisation invalide");
			}
		} else {
			throw new UserException("Veuillez saisir un email valide");
		}

	}

	public boolean isValidToken(String role, String token) {
		long id;
		try {
			id = (long) generate.parseJWT(token);
			Users information = repository.getUserById(id);
			String userRole = information.getRole();
			System.out.println("actual Role is " + userRole);
			System.out.println("expected role is" + role);
			String fetchRole = role;
			if (fetchRole.equals("admin")) {
				return true;
			} else if (fetchRole.equals("seller") && !userRole.equals("admin")) {
				return true;
			} else if (fetchRole.equals(userRole)) {
				return true;
			} else {
				throw new UserException("Vous n'êtes pas une personne autorisée");
			}
		} catch (Exception e) {
			throw new UserException("Utilisateur invalide");
		}
	}

	public Users verifyPassword(Users user, LoginInformation information) {
		if ((user.isVerified() == true)) {
			if (encryption.matches(information.getPassword(), user.getPassword())) {
				System.out.println(generate.jwtToken(user.getUserId()));
				return user;
			} else {
				throw new UserException("Mot de passe incorrect");
			}
		} else {
			// 8020 should be like the server.port in the application.properties file
			String mailResponse = response.formMessage("http://localhost:8020/user/verify",
					generate.jwtToken(user.getUserId()));
			MailServiceProvider.sendEmail(information.getEmail(), "verification", mailResponse);
			throw new UserException("Veuillez vérifier votre email");
		}
	}

	@Transactional
	@Override
	public boolean verify(String token) throws Exception {
		Long id = (long) generate.parseJWT(token);
		System.out.println("Id de l'utilisateur: " + id);
		repository.verify(id);
		return true;
	}

	@Override
	public boolean isUserExist(String email) {
		try {
			Users user = repository.getUser(email);
			if (user.isVerified() == true) {
				// 54670 should be the same port g�n�r� quand on execute ng serve --port 54670
				String mailResponse = response.formMessage("http://localhost:54670/update-password",
						generate.jwtToken(user.getUserId()));
				System.out.println(mailResponse);
				MailServiceProvider.sendEmail(user.getEmail(), "Réinitialisez votre mot de passe", mailResponse);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new UserException("L'utilisateur n'existe pas");
		}
	}

	@Transactional
	@Override
	public boolean update(PasswordUpdate information, String token) {
		System.out.println("Les informations de l'utilisateur" + information.toString());
		if (information.getNewPassword().equals(information.getConfirmPassword())) {
			Long id = null;
			try {
				id = (long) generate.parseJWT(token);
				System.out.println("Identifiant d'utilisateur " + id);
				Users UpdateUser = repository.getUser(information.getEmail());
				System.out.println("Les informations d'utilisateur mises � jour" + UpdateUser);
				if (id == UpdateUser.getUserId()) {
					String epassword = encryption.encode(information.getConfirmPassword());
					information.setConfirmPassword(epassword);

					return repository.upDate(information, id);
				} else {
					throw new UserException("Veuillez saisir un e-mail valide");
				}
			} catch (Exception e) {
				throw new UserException("Les informations d'identification invalides");
			}
		} else {
			System.out.println("Le mot de passe ne correspond pas");
			throw new UserException("Mot de passe incorrect");
		}
	}

	@Override
	public List<Users> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public Users getSingleUser(String token) {
		Long id;
		try {
			id = (long) generate.parseJWT(token);
		} catch (Exception e) {
			throw new UserException("L'utilisateur n'existe pas");
		}

		if (isValidToken("admin", token)) {
			Users user = repository.getUserById(id);
			return user;
		} else {
			throw new UserException("token is not valid");
		}
	}

}
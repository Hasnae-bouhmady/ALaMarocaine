/**
 * 
 */
package com.bridgelabz.alamarocaine.service;

import java.util.List;

import com.bridgelabz.alamarocaine.dto.UserDto;
import com.bridgelabz.alamarocaine.entity.Users;
import com.bridgelabz.alamarocaine.request.LoginInformation;
import com.bridgelabz.alamarocaine.request.PasswordUpdate;

/**
 * @author
 *
 */
public interface UserServices {

	Users login(LoginInformation information);

	boolean register(UserDto ionformation);

	boolean verify(String token) throws Exception;

	boolean isUserExist(String email);

	boolean update(PasswordUpdate information, String token);

	List<Users> getUsers();

	Users getSingleUser(String token);
}
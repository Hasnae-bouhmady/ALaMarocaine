/**
 * 
 */
package com.bridgelabz.alamarocaine.repository;

import java.util.List;

import com.bridgelabz.alamarocaine.entity.Users;
import com.bridgelabz.alamarocaine.request.PasswordUpdate;

/**
 * @author HP
 *
 */

public interface IUserRepository {
	Users save(Users users);

	Users getUser(String email);

	boolean verify(Long id);

	boolean upDate(PasswordUpdate information, Long token);

	Users getUserById(Long id);

	List<Users> getUsers();

}


// tous les commenataires sont les miens
package com.bridgelabz.alamarocaine.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.alamarocaine.entity.Chef;

@Repository
public interface ChefImple extends JpaRepository<Chef, Long> {

	@Query("from Chef where chef_id=:id")
	Chef fetchbyId(Long id);

	@Query(value = "select * from Chef", nativeQuery = true)
	List<Chef> findAllPage(Pageable pageable);

	@Query("from Chef where chef_id=:id ")
	List<Chef> fetchbyIdList(Long id);

	@Modifying
	@Query("delete from Chef where chef_id=:id")
	void deleteByChefId(long id);

	/**
	 * /// A SUPPRIMER
	 * 
	 * @Query(value = "select * from Chef where status='approved'", nativeQuery =
	 *              true) List<Chef> getApprovedPlats();
	 **/

	/**
	 * @Query(value = "select * from Plat where plat_name like %?1% AND
	 *              status='approved'", nativeQuery = true) Page<Chef>
	 *              findByPlatName(String name, Pageable pageable);
	 **/

	/**
	 * // A SUPPRIMER
	 * 
	 * @Query(value = "select * from Plat where status='OnHold'", nativeQuery =
	 *              true) List<Plat> getAllonHoldPlats();
	 **/

	/**
	 * @Query(value = "select rating and review from review_and_rating where
	 *              plat_id=:id", nativeQuery = true) List<ReviewAndRating>
	 *              reviews(Long id);
	 **/
	/***
	 * @Query(value = "select avg(rating) from review_and_rating where plat_id=:id",
	 *              nativeQuery = true) double avgRateOfPlat(long id);
	 **/

	/**
	 * // A SUPPRIMER
	 * 
	 * @Modifying @Query("update from Plat set status=:status where plat_id=:id")
	 *            int updatePlatStatusByPlatId(String status, long id);
	 ***/

	@Query(value = "select * from Chef where user_id=:id", nativeQuery = true)
	List<Chef> getAllChefs(long id);

	/**
	 * // A SUPPRIMER
	 * 
	 * @Query(value = "select * from Plat where status='approved'", nativeQuery =
	 *              true) List<Plat> getAllApprovedPlats();
	 **/

	/**
	 * // A SUPPRIMER
	 * 
	 * @Query(value = "select * from Plat where status='onhold'", nativeQuery =
	 *              true) List<Plat> getAllOnHoldPlats();
	 **/

	/**
	 * // A SUPPRIMER
	 * 
	 * @Query(value = "select * from Plat where status='rejected'", nativeQuery =
	 *              true) List<Plat> getAllRejectedPlats();
	 **/

	@Query("from Chef where chef_name=:name")
	Chef fetchbyChefName(String name);
}
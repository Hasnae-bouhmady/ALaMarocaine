package com.bridgelabz.alamarocaine.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.alamarocaine.entity.Plat;
import com.bridgelabz.alamarocaine.entity.ReviewAndRating;

@Repository
public interface PlatImple extends JpaRepository<Plat, Long> {

	@Query("from Plat where plat_id=:id")
	Plat fetchbyId(Long id);

	@Query(value = "select * from Plat", nativeQuery = true)
	List<Plat> findAllPage(Pageable pageable);

	@Query("from Plat where plat_id=:id ")
	List<Plat> fetchbyIdList(Long id);

	@Modifying
	@Query("delete from Plat where plat_id=:id")
	void deleteByPlatId(long id);

	/// A SUPPRIMER
	@Query(value = "select * from Plat where  status='approved'", nativeQuery = true)
	List<Plat> getApprovedPlats();

	@Query(value = "select * from Plat where plat_name like %?1% AND status='approved'", nativeQuery = true)
	Page<Plat> findByPlatName(String name, Pageable pageable);

	// A SUPPRIMER
	@Query(value = "select * from Plat where status='OnHold'", nativeQuery = true)
	List<Plat> getAllonHoldPlats();

	@Query(value = "select rating and review from review_and_rating where plat_id=:id", nativeQuery = true)
	List<ReviewAndRating> reviews(Long id);

	@Query(value = "select avg(rating) from review_and_rating where plat_id=:id", nativeQuery = true)
	double avgRateOfPlat(long id);

	// A SUPPRIMER
	@Modifying
	@Query("update from Plat set status=:status where plat_id=:id")
	int updatePlatStatusByPlatId(String status, long id);

	@Query(value = "select * from Plat where user_id=:id", nativeQuery = true)
	List<Plat> getAllPlats(long id);

	// A SUPPRIMER
	@Query(value = "select * from Plat where status='approved'", nativeQuery = true)
	List<Plat> getAllApprovedPlats();

	// A SUPPRIMER
	@Query(value = "select * from Plat where status='onhold'", nativeQuery = true)
	List<Plat> getAllOnHoldPlats();

	// A SUPPRIMER
	@Query(value = "select * from Plat where status='rejected'", nativeQuery = true)
	List<Plat> getAllRejectedPlats();

	@Query("from Plat where plat_name=:name")
	Plat fetchbyPlatName(String name);
}
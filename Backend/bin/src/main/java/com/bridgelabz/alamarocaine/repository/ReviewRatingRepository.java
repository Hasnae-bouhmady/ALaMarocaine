package com.bridgelabz.alamarocaine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.alamarocaine.entity.ReviewAndRating;

@Repository
public interface ReviewRatingRepository extends JpaRepository<ReviewAndRating, Long> {

//	@Query( value = "select * from review_and_rating where ", nativeQuery = true)
//    List<ReviewAndRating> getreviews(Long id);

	@Query("from ReviewAndRating where plat_id=:id ")
	List<ReviewAndRating> getreviews(Long id);

	@Query("from ReviewAndRating where plat_id=:id and name=:userName ")
	ReviewAndRating getPlatReview(Long id, String userName);

}

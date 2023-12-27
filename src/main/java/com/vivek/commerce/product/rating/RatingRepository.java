package com.vivek.commerce.product.rating;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RatingRepository extends JpaRepository<Rating, Long>{
    @Transactional
    @Modifying
    @Query("DELETE FROM Rating r WHERE r.product.id = :productId")
    void deleteAllRatingsByProductId(Long productId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Rating r WHERE r.user.id = :userId")
    void deleteAllRatingsByUserId(UUID userId);
}

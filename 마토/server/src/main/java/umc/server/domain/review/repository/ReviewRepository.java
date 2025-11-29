package umc.server.domain.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.review.entity.Review;
import umc.server.domain.store.entity.Store;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(
            value = "SELECT r1.* " +
                    "FROM review r1 " +
                    "LEFT JOIN store s1 ON r1.store_id = s1.id " +
                    "LEFT JOIN address a1 ON s1.address_id = a1.id " +
                    "WHERE a1.name LIKE CONCAT('%', :name, '%')",
            nativeQuery = true
    )
    List<Review> searchReviewByAddress(@Param("name") String name);



    @Query(
            value = "SELECT r1.* " +
                    "FROM review r1 " +
                    "LEFT JOIN store s1 ON r1.store_id = s1.id " +
                    "LEFT JOIN address a1 ON s1.address_id = a1.id " +
                    "WHERE r1.rating > :rating",
            nativeQuery = true
    )
    List<Review> searchReviewByRating(@Param("rating") Float rating);


    @Query(
            value = "SELECT r1.* " +
                    "FROM review r1 " +
                    "LEFT JOIN store s1 ON r1.store_id = s1.id " +
                    "LEFT JOIN address a1 ON s1.address_id = a1.id " +
                    "WHERE a1.name LIKE CONCAT('%', :name, '%') " +
                    "AND r1.rating > :rating",
            nativeQuery = true
    )
    List<Review> searchReviewByAddressAndRating(@Param("name") String name, @Param("rating") Float rating);

    Page<Review> findAllByStore(Store store, PageRequest pageRequest);

    Page<Review> findByUser_Id(Long userId, Pageable pageable);
}




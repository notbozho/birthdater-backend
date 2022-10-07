package dev.bozho.birthdater.repository;

import dev.bozho.birthdater.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    Optional<Friend> findByUserId(long userId);

    @Query(
            value = "SELECT * FROM Friend f " +
                    "WHERE f.user_id = ?1",
            nativeQuery = true)
    Optional<List<Friend>> findAllByUserId(long userId);
}
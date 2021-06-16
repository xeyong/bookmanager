package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Address;
import com.fastcampus.jpa.bookmanager.domain.User;
import org.apache.tomcat.jni.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);

    @Query("SELECT u FROM User u WHERE u.name =?1 OR u.id = 1L")
    Optional<User> findByOpt(String name);

    User findTop1ByName(String name);

    List<User> findTop3ByName(String name);

    List<User> findLast1ByName(String name);

    List<User> findByEmailAndName(String email, String name);
    List<User> findByEmailOrName(String email, String name);

    List<User> findByCreatedAtAfter(LocalDateTime localDateTime);

    List<User> findByCreatedAtGreaterThan(LocalDateTime localDateTime);

    List<User> findByCreatedAtBetween(LocalDateTime local1, LocalDateTime local2);

    List<User> findByIdIsNotNull();

    List<User> findByAddressIsEmpty();

    List<User> findByNameIn(List<String> names);
    List<User> findByNameLike(String name);
    List<User> findByNameStartingWith(String name);

    List<User> findTop1ByNameOrderByIdDesc(String name);
    List<User> findFirstByName(String name, Sort sort);

    Page<User> findByName(String name, Pageable pageable);
}

package com.rustudor.repository;

import com.rustudor.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<User,Integer> {
    @Query("SELECT u FROM User u JOIN FETCH u.login l where l.username = :username")
    User findByUsername(@Param("username") String username);
}

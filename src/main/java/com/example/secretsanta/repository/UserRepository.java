package com.example.secretsanta.repository;

import com.example.secretsanta.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT u FROM User u WHERE u.name = :name AND u.password = :password")
  Optional<User> findByNameAndPassword(
      @Param("name") String name, @Param("password") String password);
}

package ua.polkovnik.SpringBootApp.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.polkovnik.SpringBootApp.models.CommonUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CommonUser, String> {

    Optional<CommonUser> findByUsername(String username);
}
package com.camara.organizada.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camara.organizada.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {


}

package com.camara.organizada.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camara.organizada.models.Deputy;

@Repository
public interface DeputyRepository extends JpaRepository<Deputy, String> {

}

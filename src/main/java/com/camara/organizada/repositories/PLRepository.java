package com.camara.organizada.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camara.organizada.models.PL;

@Repository
public interface PLRepository extends JpaRepository<PL, String> {

}

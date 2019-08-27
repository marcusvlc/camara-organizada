package com.camara.organizada.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camara.organizada.models.PEC;

@Repository
public interface PECRepository extends JpaRepository<PEC, String> {

}

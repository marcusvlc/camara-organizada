package com.camara.organizada.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camara.organizada.models.PLP;

@Repository
public interface PLPRepository extends JpaRepository<PLP, String> {

}

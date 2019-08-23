package com.camara.organizada.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camara.organizada.models.Commission;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, String> {

}

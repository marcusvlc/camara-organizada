package com.camara.organizada.legistative;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PECRepository extends JpaRepository<PEC, String> {

}

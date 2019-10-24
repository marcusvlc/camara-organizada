package com.camara.organizada.legistative;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PLPRepository extends JpaRepository<PLP, String> {

}

package com.camara.organizada.deputy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeputyRepository extends JpaRepository<Deputy, Long> {

}

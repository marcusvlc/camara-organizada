package com.camara.organizada.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camara.organizada.models.RulingParty;

@Repository
public interface RulingPartyRepository extends JpaRepository<RulingParty, String>{

}

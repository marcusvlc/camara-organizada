package com.camara.organizada.ruling;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RulingPartyRepository extends JpaRepository<RulingParty, String>{

}

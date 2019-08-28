package com.camara.organizada.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camara.organizada.models.Voting;

@Repository
public interface VotingRepository extends JpaRepository<Voting, String> {

}

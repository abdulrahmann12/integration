package com.integration.bosta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integration.bosta.model.Tracking;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking, Long>{

}

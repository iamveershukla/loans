package com.devops.coe.creditrating.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devops.coe.creditrating.models.CreditRating;

public interface CreditRatingRepository  extends JpaRepository<CreditRating, String>{

}

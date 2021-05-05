package com.ex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.ex.model.ErrorDetailsResponse;
@Repository
public interface ErrorDetailsRepository extends JpaRepository<ErrorDetailsResponse,Integer> {

}
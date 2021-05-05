package com.ex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ex.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{

	public Admin findByEmpIdAndPassword(int emp_id,String password);
}

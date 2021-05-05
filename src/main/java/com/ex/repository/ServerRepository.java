package com.ex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ex.model.Server;

@Repository
public interface ServerRepository extends JpaRepository<Server,String>{

	public Server findByServerIp(String serverIp);

}
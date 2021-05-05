package com.ex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.model.Instance;

public interface InstanceRepository extends JpaRepository<Instance, String>{

	public Instance findByInstanceIp(String instanceIp);

}

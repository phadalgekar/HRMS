package com.management.HRMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.HRMS.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}

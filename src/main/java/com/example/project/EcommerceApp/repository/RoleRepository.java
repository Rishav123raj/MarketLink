package com.example.project.EcommerceApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.EcommerceApp.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}

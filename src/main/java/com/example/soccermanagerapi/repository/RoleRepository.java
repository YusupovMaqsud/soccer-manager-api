package com.example.soccermanagerapi.repository;


import com.example.soccermanagerapi.entity.Role;
import com.example.soccermanagerapi.entity.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleEnum(RoleEnum roleEnum);

}

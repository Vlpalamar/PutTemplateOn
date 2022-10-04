package com.vlad.demo.repos.reg;


import com.vlad.demo.entities.reg.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
}

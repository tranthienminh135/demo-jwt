package com.example.repository.account;


import com.example.model.account.AppUser;
import com.example.model.account.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findAllByAppUser(AppUser appUser);
}

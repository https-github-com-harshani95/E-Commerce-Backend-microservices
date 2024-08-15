package com.devstack.user_service_api.repo;


import com.devstack.user_service_api.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemUserRepo extends JpaRepository<SystemUser, String> {
}

package com.devstack.user_service_api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface SystemUser extends JpaRepository<SystemUser, String> {
}

package com.dailycodework.universalpetcare.repository;

import com.dailycodework.universalpetcare.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}

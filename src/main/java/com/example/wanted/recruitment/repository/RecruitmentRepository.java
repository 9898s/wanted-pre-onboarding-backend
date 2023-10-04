package com.example.wanted.recruitment.repository;

import com.example.wanted.recruitment.entity.Recruitment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

  @Query("SELECT r FROM Recruitment r WHERE r.company.name LIKE %:search% OR r.position LIKE %:search%")
  List<Recruitment> findByCompanyNameOrContent(String search);
}
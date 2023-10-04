package com.example.wanted.history.repository;

import com.example.wanted.history.entity.History;
import com.example.wanted.member.entity.Member;
import com.example.wanted.recruitment.entity.Recruitment;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {

  Optional<History> findByRecruitmentAndMember(Recruitment recruitment, Member member);
}
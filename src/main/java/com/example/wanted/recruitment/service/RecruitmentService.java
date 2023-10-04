package com.example.wanted.recruitment.service;

import com.example.wanted.company.entity.Company;
import com.example.wanted.company.repository.CompanyRepository;
import com.example.wanted.recruitment.entity.Recruitment;
import com.example.wanted.recruitment.model.AddRecruitment;
import com.example.wanted.recruitment.model.RecruitmentDto;
import com.example.wanted.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecruitmentService {

  private final RecruitmentRepository recruitmentRepository;
  private final CompanyRepository companyRepository;

  // 채용공고 등록
  public RecruitmentDto addRecruitment(AddRecruitment.Request request) {
    Company company = companyRepository.findById(request.getCompanyId())
        .orElseThrow(() -> new RuntimeException("찾을 수 없는 회사 번호입니다."));

    return RecruitmentDto.fromEntity(
        recruitmentRepository.save(
            Recruitment.builder()
                .company(company)
                .position(request.getPosition())
                .reward(request.getReward())
                .content(request.getContent())
                .skill(request.getSkill())
                .build()
        )
    );
  }
}

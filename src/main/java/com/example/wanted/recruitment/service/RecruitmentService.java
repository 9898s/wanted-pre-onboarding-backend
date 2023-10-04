package com.example.wanted.recruitment.service;

import com.example.wanted.company.entity.Company;
import com.example.wanted.company.repository.CompanyRepository;
import com.example.wanted.recruitment.entity.Recruitment;
import com.example.wanted.recruitment.model.AddRecruitment;
import com.example.wanted.recruitment.model.EditRecruitment.Request;
import com.example.wanted.recruitment.model.RecruitmentDto;
import com.example.wanted.recruitment.repository.RecruitmentRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  // 채용공고 수정
  @Transactional
  public RecruitmentDto editRecruitment(Long id, Request request) {
    Recruitment recruitment = recruitmentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("찾을 수 없는 채용공고 번호입니다."));

    recruitment.update(request.getPosition(), request.getReward(), request.getContent(),
        request.getSkill());

    return RecruitmentDto.fromEntity(recruitment);
  }

  // 채용공고 삭제
  @Transactional
  public void deleteRecruitment(Long id) {
    Recruitment recruitment = recruitmentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("찾을 수 없는 채용공고 번호입니다."));

    recruitmentRepository.delete(recruitment);
  }

  // 채용공고 목록
  @Transactional(readOnly = true)
  public List<RecruitmentDto> allRecruitment() {

    return recruitmentRepository.findAll().stream()
        .map(RecruitmentDto::fromEntity)
        .collect(Collectors.toList());
  }

  // 채용공고 검색
  @Transactional(readOnly = true)
  public List<RecruitmentDto> searchRecruitment(String search) {

    return recruitmentRepository.findByCompanyNameOrContent(search).stream()
        .map(RecruitmentDto::fromEntity)
        .collect(Collectors.toList());
  }
}

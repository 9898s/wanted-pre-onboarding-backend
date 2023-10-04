package com.example.wanted.recruitment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.wanted.company.repository.CompanyRepository;
import com.example.wanted.global.exception.CompanyException;
import com.example.wanted.global.exception.type.ErrorCode;
import com.example.wanted.recruitment.entity.Recruitment;
import com.example.wanted.recruitment.model.AddRecruitment;
import com.example.wanted.recruitment.repository.RecruitmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class RecruitmentServiceTest {

  @Autowired
  private RecruitmentRepository recruitmentRepository;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private RecruitmentService recruitmentService;

  @Test
  void 채용공고_등록_테스트_성공() {
    // given
    AddRecruitment.Request request = new AddRecruitment.Request();
    request.setCompanyId(1L);
    request.setPosition("백엔드 주니어 개발자");
    request.setReward(1000000L);
    request.setContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..");
    request.setSkill("Python");

    // when
    Long id = recruitmentService.addRecruitment(request).getId();
    Recruitment recruitment = recruitmentRepository.findById(id).get();

    // then
    assertEquals(id, recruitment.getId());
  }

  @Test
  void 채용공고_등록_테스트_실패() {
    // given
    AddRecruitment.Request request = new AddRecruitment.Request();
    request.setCompanyId(5L);
    request.setPosition("백엔드 주니어 개발자");
    request.setReward(1000000L);
    request.setContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..");
    request.setSkill("Python");

    // when
    CompanyException companyException = Assertions.assertThrows(CompanyException.class,
        () -> recruitmentService.addRecruitment(request));

    // then
    assertEquals(ErrorCode.INVALID_COMPANY_ID, companyException.getErrorCode());
  }
}
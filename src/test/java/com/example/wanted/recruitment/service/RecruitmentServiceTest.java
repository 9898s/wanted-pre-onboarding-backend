package com.example.wanted.recruitment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.wanted.company.entity.Company;
import com.example.wanted.company.repository.CompanyRepository;
import com.example.wanted.global.exception.CompanyException;
import com.example.wanted.global.exception.RecruitmentException;
import com.example.wanted.global.exception.type.ErrorCode;
import com.example.wanted.recruitment.entity.Recruitment;
import com.example.wanted.recruitment.model.AddRecruitment;
import com.example.wanted.recruitment.model.EditRecruitment;
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

  @Test
  void 채용공고_수정_테스트_성공() {
    // given
    Recruitment recruitment = recruitmentRepository.save(
        Recruitment.builder()
            .position("백엔드 주니어 개발자")
            .build()
    );

    EditRecruitment.Request request = new EditRecruitment.Request();
    request.setPosition("프론트엔드 주니어 개발자");

    // when
    String position = recruitmentService.editRecruitment(recruitment.getId(), request)
        .getPosition();

    // then
    assertEquals(position, request.getPosition());
  }

  @Test
  void 채용공고_수정_테스트_실패() {
    // given
    Recruitment recruitment = recruitmentRepository.save(
        Recruitment.builder()
            .position("백엔드 주니어 개발자")
            .build()
    );

    EditRecruitment.Request request = new EditRecruitment.Request();
    request.setPosition("프론트엔드 주니어 개발자");

    // when
    RecruitmentException recruitmentException = Assertions.assertThrows(RecruitmentException.class,
        () -> recruitmentService.editRecruitment(2L, request));

    // then
    assertEquals(ErrorCode.INVALID_RECRUITMENT_ID, recruitmentException.getErrorCode());
  }

  @Test
  void 채용공고_삭제_테스트_성공() {
    // given
    Recruitment recruitment = recruitmentRepository.save(
        Recruitment.builder()
            .position("백엔드 주니어 개발자")
            .build()
    );

    // when
    recruitmentService.deleteRecruitment(1L);

    // then
    assertEquals(0, recruitmentRepository.count());
  }

  @Test
  void 채용공고_삭제_테스트_실패() {
    // given
    Recruitment recruitment = recruitmentRepository.save(
        Recruitment.builder()
            .position("백엔드 주니어 개발자")
            .build()
    );

    // when
    RecruitmentException recruitmentException = Assertions.assertThrows(RecruitmentException.class,
        () -> recruitmentService.deleteRecruitment(2L));

    // then
    assertEquals(ErrorCode.INVALID_RECRUITMENT_ID, recruitmentException.getErrorCode());
  }

  @Test
  void 채용공고_목록_테스트() {
    // given
    recruitmentRepository.save(
        Recruitment.builder()
            .position("백엔드 주니어 개발자")
            .build()
    );

    recruitmentRepository.save(
        Recruitment.builder()
            .position("프론트 주니어 개발자")
            .build()
    );

    // when
    int size = recruitmentService.allRecruitment().size();

    // then
    assertEquals(2, size);
  }

  @Test
  void 채용공고_검색_테스트() {
    // given
    Company company = companyRepository.save(
        Company.builder()
            .name("원티드 연구실")
            .nation("한국")
            .location("서울")
            .build()
    );

    recruitmentRepository.save(
        Recruitment.builder()
            .company(company)
            .position("Django 백엔드 개발자")
            .build()
    );

    recruitmentRepository.save(
        Recruitment.builder()
            .company(company)
            .position("Django 백엔드 개발자")
            .build()
    );

    // when
    int size = recruitmentService.searchRecruitment("Django").size();

    // then
    assertEquals(2, size);
  }
}
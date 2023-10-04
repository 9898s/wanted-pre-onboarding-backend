package com.example.wanted.history.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.wanted.global.exception.HistoryException;
import com.example.wanted.global.exception.MemberException;
import com.example.wanted.global.exception.RecruitmentException;
import com.example.wanted.global.exception.type.ErrorCode;
import com.example.wanted.history.entity.History;
import com.example.wanted.history.model.AddHistory;
import com.example.wanted.history.repository.HistoryRepository;
import com.example.wanted.member.entity.Member;
import com.example.wanted.member.repository.MemberRepository;
import com.example.wanted.recruitment.entity.Recruitment;
import com.example.wanted.recruitment.repository.RecruitmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HistoryServiceTest {

  @Autowired
  private HistoryRepository historyRepository;

  @Autowired
  private RecruitmentRepository recruitmentRepository;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private HistoryService historyService;

  @BeforeEach
  void beforeEach() {
    historyRepository.deleteAll();
    recruitmentRepository.deleteAll();
    memberRepository.deleteAll();
  }

  @Test
  void 채원공고_지원_테스트_성공() {
    // given
    Recruitment recruitment = recruitmentRepository.save(
        Recruitment.builder()
            .position("백엔드 주니어 개발자")
            .build()
    );

    Member member = memberRepository.save(
        Member.builder()
            .email("test@test.com")
            .build()
    );

    AddHistory.Request request = new AddHistory.Request();
    request.setRecruitmentId(recruitment.getId());
    request.setMemberId(member.getId());

    // when
    Long id = historyService.addHistory(request).getId();
    History history = historyRepository.findById(id).get();

    // then
    assertEquals(history.getId(), id);
  }

  @Test
  void 채원공고_지원_테스트_없는채용_실패() {
    // given
    Recruitment recruitment = recruitmentRepository.save(
        Recruitment.builder()
            .position("백엔드 주니어 개발자")
            .build()
    );

    Member member = memberRepository.save(
        Member.builder()
            .email("test@test.com")
            .build()
    );

    AddHistory.Request request = new AddHistory.Request();
    request.setRecruitmentId(0L);
    request.setMemberId(member.getId());

    // when
    RecruitmentException recruitmentException = Assertions.assertThrows(RecruitmentException.class,
        () -> historyService.addHistory(request));

    // then
    assertEquals(ErrorCode.INVALID_RECRUITMENT_ID, recruitmentException.getErrorCode());
  }

  @Test
  void 채원공고_지원_테스트_없는회원_실패() {
    // given
    Recruitment recruitment = recruitmentRepository.save(
        Recruitment.builder()
            .position("백엔드 주니어 개발자")
            .build()
    );

    Member member = memberRepository.save(
        Member.builder()
            .email("test@test.com")
            .build()
    );

    AddHistory.Request request = new AddHistory.Request();
    request.setRecruitmentId(recruitment.getId());
    request.setMemberId(0L);

    // when
    MemberException memberException = Assertions.assertThrows(MemberException.class,
        () -> historyService.addHistory(request));

    // then
    assertEquals(ErrorCode.INVALID_MEMBER_ID, memberException.getErrorCode());
  }

  @Test
  void 채원공고_지원_테스트_이미존재_실패() {
    // given
    Recruitment recruitment = recruitmentRepository.save(
        Recruitment.builder()
            .position("백엔드 주니어 개발자")
            .build()
    );

    Member member = memberRepository.save(
        Member.builder()
            .email("test@test.com")
            .build()
    );

    historyRepository.save(
        History.builder()
            .recruitment(recruitment)
            .member(member)
            .build()
    );

    AddHistory.Request request = new AddHistory.Request();
    request.setRecruitmentId(recruitment.getId());
    request.setMemberId(member.getId());

    // when
    HistoryException historyException = Assertions.assertThrows(HistoryException.class,
        () -> historyService.addHistory(request));

    // then
    assertEquals(ErrorCode.ALREADY_RECRUITMENT_MEMBER, historyException.getErrorCode());
  }
}
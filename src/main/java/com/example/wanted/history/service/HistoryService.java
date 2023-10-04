package com.example.wanted.history.service;

import static com.example.wanted.global.exception.type.ErrorCode.ALREADY_RECRUITMENT_MEMBER;
import static com.example.wanted.global.exception.type.ErrorCode.INVALID_MEMBER_ID;
import static com.example.wanted.global.exception.type.ErrorCode.INVALID_RECRUITMENT_ID;

import com.example.wanted.global.exception.HistoryException;
import com.example.wanted.global.exception.MemberException;
import com.example.wanted.global.exception.RecruitmentException;
import com.example.wanted.history.entity.History;
import com.example.wanted.history.model.AddHistory;
import com.example.wanted.history.model.HistoryDto;
import com.example.wanted.history.repository.HistoryRepository;
import com.example.wanted.member.entity.Member;
import com.example.wanted.member.repository.MemberRepository;
import com.example.wanted.recruitment.entity.Recruitment;
import com.example.wanted.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HistoryService {

  private final HistoryRepository historyRepository;
  private final RecruitmentRepository recruitmentRepository;
  private final MemberRepository memberRepository;

  // 채용공고 지원
  public HistoryDto addHistory(AddHistory.Request request) {
    Recruitment recruitment = recruitmentRepository.findById(request.getRecruitmentId())
        .orElseThrow(() -> new RecruitmentException(INVALID_RECRUITMENT_ID));

    Member member = memberRepository.findById(request.getMemberId())
        .orElseThrow(() -> new MemberException(INVALID_MEMBER_ID));

    if (historyRepository.findByRecruitmentAndMember(recruitment, member).isPresent()) {
      throw new HistoryException(ALREADY_RECRUITMENT_MEMBER);
    }

    return HistoryDto.fromEntity(
        historyRepository.save(
            History.builder()
                .recruitment(recruitment)
                .member(member)
                .build()
        )
    );
  }
}

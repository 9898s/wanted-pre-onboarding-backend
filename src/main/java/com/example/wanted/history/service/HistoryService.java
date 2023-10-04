package com.example.wanted.history.service;

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
        .orElseThrow(() -> new RuntimeException("찾을 수 없는 채용공고 번호입니다."));

    Member member = memberRepository.findById(request.getMemberId())
        .orElseThrow(() -> new RuntimeException("찾을 수 없는 회원 번호입니다."));

    if (historyRepository.findByRecruitmentAndMember(recruitment, member).isPresent()) {
      throw new RuntimeException("이미 지원한 공고입니다.");
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

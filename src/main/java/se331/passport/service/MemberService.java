package se331.passport.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.passport.entity.Member;
import se331.passport.entity.ExamHistory;
import se331.passport.entity.MemberDTO;

import java.util.List;

public interface MemberService {
    Page<Member> getMembers(String name, String surname, String province, String level, Pageable page);
    Member getMember(Long id);
    Member setActive(Long id, Boolean active);
    List<ExamHistory> getExamHistories(Long id);
    Member updateMember(Long id, MemberDTO data);
}
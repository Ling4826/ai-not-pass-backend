package se331.passport.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.passport.dao.MemberDao;
import se331.passport.entity.Member;
import se331.passport.dao.ExamHistoryDao;
import se331.passport.entity.ExamHistory;
import se331.passport.entity.MemberDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    final MemberDao memberDao;
    final ExamHistoryDao examHistoryDao;

    @Override
    public Page<Member> getMembers(String name, String surname, String province, String level, Pageable page) {
        return memberDao.getMembers(name, surname, province, level, page);
    }

    @Override
    public Member getMember(Long id) {
        return memberDao.getMember(id);
    }

    @Override
    @Transactional
    public Member setActive(Long id, Boolean active) {
        Member member = memberDao.getMember(id);
        if (member == null) {
            return null;
        }
        member.setActive(active);
        if (member.getUser() != null) {
            member.getUser().setEnabled(active);
        }
        return memberDao.save(member);
    }

    @Override
    public List<ExamHistory> getExamHistories(Long id){
        return examHistoryDao.findByMemberId(id);
    }

    @Override
    @Transactional
    public Member updateMember(Long id, MemberDTO data) {
        Member member = memberDao.getMember(id);
        if (member == null) {
            return null;
        }
        member.setName(data.getName());
        member.setSurname(data.getSurname());
        member.setEmail(data.getEmail());
        member.setProvince(data.getProvince());
        return memberDao.save(member);
    }
}
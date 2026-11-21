package se331.passport.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.passport.entity.Member;

import java.util.List;

public interface MemberDao {
    Page<Member> getMembers(String name, String surname, String province, String level, Pageable page);
    Member getMember(Long id);
    Member save(Member member);
}
package se331.passport.dao;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import se331.passport.entity.Member;
import se331.passport.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberDaoImpl implements MemberDao {
    final MemberRepository memberRepository;


    @Override
    public Page<Member> getMembers(String name, String surname, String province, String level, Pageable page) {
        return memberRepository.findByNameIgnoreCaseContainingAndSurnameIgnoreCaseContainingAndProvinceIgnoreCaseContainingAndLevel_NameIgnoreCaseContaining(
                name, surname, province, level, page);
    }

    @Override
    public Member getMember(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }
}
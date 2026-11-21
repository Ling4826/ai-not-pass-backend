package se331.passport.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.passport.entity.Member;
import se331.passport.security.user.User;
import se331.passport.service.MemberService;
import se331.passport.util.PassportMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.Map;
import se331.passport.entity.MemberDTO;
import org.springframework.util.StringUtils;

@RestController
@RequiredArgsConstructor
public class MemberController {
    final MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<?> getMembers(
            @RequestParam(value = "_limit", required = false) Integer perPage,
            @RequestParam(value = "_page", required = false) Integer page,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "surname", defaultValue = "") String surname,
            @RequestParam(value = "province", defaultValue = "") String province,
            @RequestParam(value = "level", defaultValue = "") String level) {
        perPage = perPage == null ? 10 : perPage;
        page = page == null ? 1 : page;
        Page<Member> pageOutput = memberService.getMembers(name, surname, province, level,
                PageRequest.of(page - 1, perPage, Sort.by("id")));
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(PassportMapper.INSTANCE.getMemberDto(pageOutput.getContent()), responseHeader, HttpStatus.OK);
    }

    @GetMapping("/members/me")
    public ResponseEntity<?> getMe(@AuthenticationPrincipal User user) {
        if (user.getMember() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user has no member profile");
        }
        return ResponseEntity.ok(PassportMapper.INSTANCE.getMemberDto(memberService.getMember(user.getMember().getId())));
    }

    @PutMapping("/members/me")
    public ResponseEntity<?> updateMe(@AuthenticationPrincipal User user, @RequestBody MemberDTO body) {
        if (user.getMember() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user has no member profile");
        }
        checkMemberData(body);
        Member output = memberService.updateMember(user.getMember().getId(), body);
        return ResponseEntity.ok(PassportMapper.INSTANCE.getMemberDto(output));
    }

    private void checkMemberData(MemberDTO body) {
        if (!StringUtils.hasText(body.getName()) || !StringUtils.hasText(body.getSurname())
                || !StringUtils.hasText(body.getEmail()) || !StringUtils.hasText(body.getProvince())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name, surname, email and province are required");
        }
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<?> getMember(@PathVariable("id") Long id) {
        Member output = memberService.getMember(id);
        if (output != null) {
            return ResponseEntity.ok(PassportMapper.INSTANCE.getMemberDto(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }

    @GetMapping("/members/{id}/exam-histories")
    public ResponseEntity<?> getExamHistories(@PathVariable("id") Long id) {
        if (memberService.getMember(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
        return ResponseEntity.ok(PassportMapper.INSTANCE.getExamHistoryDto(memberService.getExamHistories(id)));
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<?> updateMember(@PathVariable("id") Long id, @RequestBody MemberDTO body) {
        checkMemberData(body);
        Member output = memberService.updateMember(id, body);
        if (output == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
        return ResponseEntity.ok(PassportMapper.INSTANCE.getMemberDto(output));
    }

    @PutMapping("/members/{id}/active")
    public ResponseEntity<?> setActive(@PathVariable("id") Long id, @RequestBody Map<String, Boolean> body){
        Boolean active = body.get("active");
        if  (active == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Active field is required");
        }
        Member output = memberService.setActive(id, active);
        if (output == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
        return ResponseEntity.ok(PassportMapper.INSTANCE.getMemberDto(output));
    }
}
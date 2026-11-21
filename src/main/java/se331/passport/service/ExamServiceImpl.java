package se331.passport.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import se331.passport.dao.ExamHistoryDao;
import se331.passport.dao.MemberDao;
import se331.passport.dao.QuestionDao;
import se331.passport.entity.ExamAnswerDTO;
import se331.passport.entity.ExamHistory;
import se331.passport.entity.ExamHistoryDTO;
import se331.passport.entity.ExamQuestionDTO;
import se331.passport.entity.ExamResultDTO;
import se331.passport.entity.ExamSubmitDTO;
import se331.passport.entity.Level;
import se331.passport.entity.Member;
import se331.passport.entity.Question;
import se331.passport.security.user.User;
import se331.passport.util.PassportMapper;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {
    final QuestionDao questionDao;
    final ExamHistoryDao examHistoryDao;
    final MemberDao memberDao;

    @Override
    public List<ExamQuestionDTO> getExamQuestions(User user) {
        Member member = user.getMember();
        if (member == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user has no member profile");
        }
        int levelNumber = member.getLevel().getLevelNumber();
        List<Question> questions = questionDao.getQuestionsByLevel(levelNumber);
        if (questions.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No exam for this level");
        }
        return PassportMapper.INSTANCE.getExamQuestionDto(questions);
    }

    @Override
    @Transactional
    public ExamResultDTO submitExam(User user, ExamSubmitDTO dto) {
        Member member = user.getMember();
        if (member == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user has no member profile");
        }
        Level level = member.getLevel();
        Optional<Level> next = questionDao.findLevelByNumber(level.getLevelNumber() + 1);
        if (next.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are already at the highest level");
        }
        List<Question> questions = questionDao.getQuestionsByLevel(level.getLevelNumber());
        if (questions.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No exam for this level");
        }
        if (dto.getAnswers() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Answer every question");
        }

        Map<Long, Integer> chosen = new HashMap<>();
        for (ExamAnswerDTO a : dto.getAnswers()) {
            chosen.put(a.getQuestionId(), a.getAnswer());
        }

        int correct = 0;
        for (Question q : questions) {
            Integer a = chosen.get(q.getId());
            if (a == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Answer every question");
            }
            if (a.equals(q.getCorrectAnswer())) {
                correct++;
            }
        }

        int score = (int) Math.round(correct * 100.0 / questions.size());
        boolean passed = score >= level.getPassCriteria();

        examHistoryDao.save(ExamHistory.builder()
                .score(score).passed(passed).attemptedAt(LocalDateTime.now())
                .member(member).level(level).build());

        if (passed) {
            member.setLevel(next.get());
            memberDao.save(member);
        }

        return ExamResultDTO.builder()
                .score(score).correct(correct).total(questions.size()).passed(passed)
                .levelNumber(member.getLevel().getLevelNumber()).leveledUp(passed)
                .build();
    }

    @Override
    public List<ExamHistoryDTO> getMyExamHistories(User user) {
        Member member = user.getMember();
        if (member == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user has no member profile");
        }
        return PassportMapper.INSTANCE.getExamHistoryDto(examHistoryDao.findByMemberId(member.getId()));
    }
}

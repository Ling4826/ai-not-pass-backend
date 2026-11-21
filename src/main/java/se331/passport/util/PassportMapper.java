package se331.passport.util;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import se331.passport.entity.Benefit;
import se331.passport.entity.BenefitDTO;
import se331.passport.entity.ELearning;
import se331.passport.entity.ELearningDTO;
import se331.passport.entity.ExamHistory;
import se331.passport.entity.ExamHistoryDTO;
import se331.passport.entity.ExamQuestionDTO;
import se331.passport.entity.Member;
import se331.passport.entity.MemberDTO;
import se331.passport.entity.Question;
import se331.passport.entity.QuestionDTO;
import se331.passport.entity.RecentExamDTO;
import se331.passport.entity.ServiceItem;
import se331.passport.entity.ServiceItemDTO;
import se331.passport.entity.UserAuthDTO;
import se331.passport.security.user.User;
import se331.passport.entity.Level;
import se331.passport.entity.LevelDTO;


@Mapper
public interface PassportMapper {
    PassportMapper INSTANCE = Mappers.getMapper(PassportMapper.class);
    MemberDTO getMemberDto(Member member);
    List<MemberDTO> getMemberDto(List<Member> members);

    @Mapping(target = "memberId", source = "member.id")
    UserAuthDTO getUserAuthDTO(User user);

    ELearningDTO getELearningDto(ELearning eLearning);

    List<ELearningDTO> getELearningDto(List<ELearning> eLearnings);

    QuestionDTO getQuestionDto(Question question);

    List<QuestionDTO> getQuestionDto(List<Question> questions);

    ExamQuestionDTO getExamQuestionDto(Question question);

    List<ExamQuestionDTO> getExamQuestionDto(List<Question> questions);

    @Mapping(source = "level.levelNumber", target = "levelNumber")
    ExamHistoryDTO getExamHistoryDto(ExamHistory history);

    List<ExamHistoryDTO> getExamHistoryDto(List<ExamHistory> histories);

    @Mapping(source = "member.id", target = "memberId")
    @Mapping(source = "member.name", target = "memberName")
    @Mapping(source = "member.surname", target = "memberSurname")
    @Mapping(source = "level.levelNumber", target = "levelNumber")
    RecentExamDTO getRecentExamDto(ExamHistory history);

    List<RecentExamDTO> getRecentExamDto(List<ExamHistory> histories);

    BenefitDTO getBenefitDto(Benefit benefit);

    List<BenefitDTO> getBenefitDto(List<Benefit> benefits);

    ServiceItemDTO getServiceItemDto(ServiceItem serviceItem);

    List<ServiceItemDTO> getServiceItemDto(List<ServiceItem> serviceItems);

    LevelDTO getLevelDto(Level level);
    List<LevelDTO> getLevelDto(List<Level> levels);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "level", ignore = true)
    Question getQuestion(QuestionDTO questionDTO);
}
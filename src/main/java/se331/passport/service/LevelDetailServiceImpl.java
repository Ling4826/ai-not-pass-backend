package se331.passport.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import se331.passport.dao.LevelDetailDao;
import se331.passport.entity.Level;
import se331.passport.entity.LevelDetailDTO;
import se331.passport.entity.Member;
import se331.passport.security.user.User;
import se331.passport.util.PassportMapper;

@Service
@RequiredArgsConstructor
public class LevelDetailServiceImpl implements LevelDetailService {
    final LevelDetailDao levelDetailDao;

    @Override
    public LevelDetailDTO getMyLevelDetail(User user) {
        Member member = user.getMember();
        if (member == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user has no member profile");
        }
        Level level = member.getLevel();
        int levelNumber = level.getLevelNumber();
        return LevelDetailDTO.builder()
                .levelNumber(levelNumber)
                .name(level.getName())
                .benefits(PassportMapper.INSTANCE.getBenefitDto(levelDetailDao.getBenefitsByLevel(levelNumber)))
                .services(PassportMapper.INSTANCE.getServiceItemDto(levelDetailDao.getServicesUpToLevel(levelNumber)))
                .build();
    }
}

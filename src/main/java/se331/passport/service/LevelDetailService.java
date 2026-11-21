package se331.passport.service;

import se331.passport.entity.LevelDetailDTO;
import se331.passport.security.user.User;

public interface LevelDetailService {
    LevelDetailDTO getMyLevelDetail(User user);
}

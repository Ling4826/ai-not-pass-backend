package se331.passport.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ELearningDTO {
    private Long id;
    private String title;
    private String videoUrl;
    private MemberLevelDTO level;
}

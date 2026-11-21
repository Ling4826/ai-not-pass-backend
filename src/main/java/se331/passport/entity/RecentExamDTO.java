package se331.passport.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecentExamDTO {
    Long id;
    Long memberId;
    String memberName;
    String memberSurname;
    Integer levelNumber;
    Integer score;
    Boolean passed;
    LocalDateTime attemptedAt;
}
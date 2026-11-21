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
public class ExamHistoryDTO {
    private Long id;
    private Integer score;
    private Boolean passed;
    private LocalDateTime attemptedAt;
    private Integer levelNumber;
}

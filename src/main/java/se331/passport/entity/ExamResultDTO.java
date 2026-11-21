package se331.passport.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamResultDTO {
    private Integer score;
    private Integer correct;
    private Integer total;
    private Boolean passed;
    private Integer levelNumber;
    private Boolean leveledUp;
}

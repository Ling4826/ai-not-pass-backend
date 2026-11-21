package se331.passport.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LevelStatsDTO {
    Integer levelNumber;
    String name;
    Long memberCount;
    Long examCount;
    Long passedCount;
}
package se331.passport.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LevelDetailDTO {
    Integer levelNumber;
    String name;
    List<BenefitDTO> benefits;
    List<ServiceItemDTO> services;
}

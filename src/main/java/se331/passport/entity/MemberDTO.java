package se331.passport.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    Long id;
    String name;
    String surname;
    String email;

    String province;
    MemberLevelDTO level;
    String image;
    Boolean active;
}

package se331.passport.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    Integer levelNumber;
    String name;
    Integer passCriteria;

    @OneToMany(mappedBy = "level")
    @Builder.Default
    List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "level")
    @OrderBy("id ASC")
    @Builder.Default
    List<Benefit> benefits = new ArrayList<>();

    @OneToMany(mappedBy = "level")
    @OrderBy("id ASC")
    @Builder.Default
    List<ServiceItem> services = new ArrayList<>();

    @OneToMany(mappedBy = "level")
    @Builder.Default
    List<ELearning> elearnings = new ArrayList<>();

    @OneToMany(mappedBy = "level")
    @Builder.Default
    List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "level")
    @Builder.Default
    List<ExamHistory> examHistories  = new ArrayList<>();

}
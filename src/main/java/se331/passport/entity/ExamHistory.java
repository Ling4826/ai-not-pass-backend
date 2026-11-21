package se331.passport.entity;

import jakarta.persistence.*;
        import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor

@AllArgsConstructor
public class ExamHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    Integer score;
    Boolean passed;
    LocalDateTime attemptedAt;
    @ManyToOne
    Member member;
    @ManyToOne
    Level level;
}
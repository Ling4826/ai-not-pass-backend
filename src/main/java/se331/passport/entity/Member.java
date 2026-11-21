package se331.passport.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se331.passport.security.user.User;

import java.util.ArrayList;

import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String surname;
    String email;
    String province;
    @ManyToOne
    Level level;
    String image;
    Boolean active;

    @OneToMany(mappedBy = "member")
    @Builder.Default
    List<ExamHistory> examHistories = new ArrayList<>();

    @OneToOne
    User user;


}
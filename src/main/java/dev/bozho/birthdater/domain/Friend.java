package dev.bozho.birthdater.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long friendId;

    @Column(columnDefinition = "DATE")
    private LocalDate birthdate;

    private long userId;

    public Friend(LocalDate birthdate, long userId) {
        this.birthdate = birthdate;
        this.userId = userId;
    }
}

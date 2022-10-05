package dev.bozho.birthdater.domain;

import dev.bozho.birthdater.domain.enums.FriendType;
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

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private FriendType friendType;

    @Column(columnDefinition = "DATE")
    private LocalDate birthdate;

    private long userId;

    public Friend(String firstName, String lastName, LocalDate birthdate, long userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.userId = userId;
    }
}

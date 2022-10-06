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
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FriendType friendType;
    @Column(columnDefinition = "DATE")
    private LocalDate birthdate;
    @Column(nullable = false)
    private long userId;

    public Friend(String firstName, String lastName, FriendType friendType, LocalDate birthdate, long userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.friendType = friendType;
        this.birthdate = birthdate;
        this.userId = userId;
    }

    public Friend(String firstName, String lastName, LocalDate birthdate, long userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.userId = userId;
    }
}

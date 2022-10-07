package dev.bozho.birthdater.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class UserSettings {

    @Id
    @Column(nullable = false)
    private Long settingsId;

    @Column(nullable = false)
    private Long userId;



}

package dev.bozho.birthdater.domain;

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
public class AppUserSettings {

    @Id
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long userId;



}

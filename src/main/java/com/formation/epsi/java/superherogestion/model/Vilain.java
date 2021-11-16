package com.formation.epsi.java.superherogestion.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vilain")
@Data
@NoArgsConstructor
public class Vilain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            length = 100
    )
    private String name;

    @Column(
            name = "secret_identity"
    )
    private String secretIdentity;

    @ManyToMany(mappedBy = "vilains")
    private List<SuperHero> superHeroes;

    @OneToOne(mappedBy = "nemesis")
    private SuperHero nemesis;
}

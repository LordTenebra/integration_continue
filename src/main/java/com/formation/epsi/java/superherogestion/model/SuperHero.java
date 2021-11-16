package com.formation.epsi.java.superherogestion.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "superhero")
@Data
@NoArgsConstructor
public class SuperHero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // long

    @Column(
            name = "superhero_name", // superhero-name
            nullable = false, //true
            length = 100 // 255
    )
    private String superHeroName;

    @Column(
            name = "secret_identity"
            // nullable = true,
            // length = 255
    )
    private String secretIdentity;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "superhero_power", // default => super_heroes_powers
            joinColumns = {@JoinColumn(name = "superhero_id")}, // default => super_heroes_id
            inverseJoinColumns = {@JoinColumn(name = "power_id")} // default => power_id
    )
    private List<Power> powers;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "superhero_vilain",
            joinColumns = {@JoinColumn(name = "superhero_id")},
            inverseJoinColumns = {@JoinColumn(name = "vilain_id")}
    )
    private List<Vilain> vilains;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "nemesis_id", referencedColumnName = "id")
    private Vilain nemesis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id") // default => mentor
    private SuperHero mentor;

    @OneToMany(mappedBy = "mentor", cascade = CascadeType.PERSIST)
    private List<SuperHero> sidekicks;
}

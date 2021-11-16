package com.formation.epsi.java.superherogestion.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "power")
@Data
@NoArgsConstructor
public class Power {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            nullable = false
    )
    private String name;
    private String description;

    @ManyToMany(mappedBy = "powers")
    private List<SuperHero> superHeroes;

}

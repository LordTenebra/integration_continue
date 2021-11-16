package com.formation.epsi.java.superherogestion.DTO;

import lombok.*;

@Data
@Builder
public class SuperHeroDTO {
    private long id;
    private String superHeroName;
    private String secretIdentity;
}

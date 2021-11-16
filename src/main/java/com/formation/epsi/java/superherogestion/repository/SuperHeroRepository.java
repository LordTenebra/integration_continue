package com.formation.epsi.java.superherogestion.repository;

import com.formation.epsi.java.superherogestion.model.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SuperHeroRepository extends JpaRepository<SuperHero, Long> {
}

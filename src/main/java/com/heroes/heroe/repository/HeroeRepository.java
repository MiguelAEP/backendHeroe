package com.heroes.heroe.repository;

import com.heroes.heroe.entidad.Heroe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroeRepository extends JpaRepository<Heroe,Long> {
}

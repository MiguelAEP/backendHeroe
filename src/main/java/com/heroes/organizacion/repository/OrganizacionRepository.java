package com.heroes.organizacion.repository;

import com.heroes.organizacion.entidad.Organizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizacionRepository extends JpaRepository<Organizacion,Long> {
}

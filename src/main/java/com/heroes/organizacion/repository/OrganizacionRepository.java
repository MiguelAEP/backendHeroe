package com.heroes.organizacion.repository;

import com.heroes.organizacion.entidad.Organizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizacionRepository extends JpaRepository<Organizacion,Long> {

    @Query("SELECT COUNT(o) FROM Organizacion o")
    long countAllOrganizaciones();


}

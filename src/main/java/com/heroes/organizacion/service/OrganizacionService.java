package com.heroes.organizacion.service;

import com.heroes.heroe.entidad.Heroe;
import com.heroes.organizacion.entidad.Organizacion;

import java.util.List;
import java.util.Optional;

public interface OrganizacionService {

    public List<Organizacion> findAll();

    public Integer countOrganization();
    public Organizacion createOrganizacion(Organizacion organizacion);

    public Organizacion updateOrganizacion(Organizacion organizacion);

    public Optional<Organizacion> getOrganizacionByID(Long id);

    void deleteOrganizacionById(Long id);
}

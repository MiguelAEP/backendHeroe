package com.heroes.organizacion.service;

import com.heroes.heroe.entidad.Heroe;
import com.heroes.organizacion.entidad.Organizacion;
import com.heroes.organizacion.repository.OrganizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizacionServiceIMP implements OrganizacionService{

    @Autowired
    private OrganizacionRepository organizacionRepository;
    @Override
    public List<Organizacion> findAll() {
        return organizacionRepository.findAll();
    }

    @Override
    public Integer countOrganization() {
        Long cant = organizacionRepository.countAllOrganizaciones();
        return Math.toIntExact(cant);
    }

    @Override
    public Organizacion createOrganizacion(Organizacion organizacion) {
        return organizacionRepository.save(organizacion);
    }

    @Override
    public Organizacion updateOrganizacion(Organizacion organizacion) {
        return organizacionRepository.save(organizacion);
    }

    @Override
    public Optional<Organizacion> getOrganizacionByID(Long id) {
        return organizacionRepository.findById(id);
    }

    @Override
    public void deleteOrganizacionById(Long id) {
        Organizacion organizacionExiste = this.getOrganizacionByID(id).orElseThrow();
        organizacionRepository.delete(organizacionExiste);
    }
}

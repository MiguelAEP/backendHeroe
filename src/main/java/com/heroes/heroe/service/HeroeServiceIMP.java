package com.heroes.heroe.service;

import com.heroes.heroe.entidad.Heroe;
import com.heroes.heroe.entidad.HeroeDTO;
import com.heroes.heroe.repository.HeroeRepository;
import com.heroes.organizacion.entidad.Organizacion;
import com.heroes.organizacion.repository.OrganizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HeroeServiceIMP implements HeroeService{

    @Autowired
    private HeroeRepository heroeRepository;

    @Autowired
    private OrganizacionRepository organizacionRepository;


    @Override
    public List<HeroeDTO> findAll() {
        System.out.println("hola");

       return  heroeRepository.findAll()
                .stream()
                .map( heroe ->{
                    HeroeDTO heroeDTOP = new HeroeDTO();
                    heroeDTOP.setId(heroe.getId());
                    heroeDTOP.setName(heroe.getName());
                    heroeDTOP.setDescription(heroe.getDescription());
                    heroeDTOP.setPoder(heroe.getPoder());
                    heroeDTOP.setImagen(heroe.getImagen());
                    heroeDTOP.setOrganizacionID(heroe.getOrganizacion().getId());
                    return heroeDTOP;
                }).collect(Collectors.toList());


    }

    @Override
    public HeroeDTO createHeroe(HeroeDTO heroeDTOP) {

        Heroe heroe = new Heroe();
        heroe.setName(heroeDTOP.getName());
        heroe.setDescription(heroeDTOP.getDescription());
        heroe.setPoder(heroeDTOP.getPoder());
        heroe.setImagen(heroeDTOP.getImagen());

        Organizacion organizacion = organizacionRepository.getReferenceById(heroeDTOP.getOrganizacionID());
        heroe.setOrganizacion(organizacion);
        heroeRepository.save(heroe);

        HeroeDTO heroeDTOP1 = new HeroeDTO();
        heroeDTOP1.setName(heroe.getName());
        heroeDTOP1.setDescription(heroe.getDescription());
        heroeDTOP1.setPoder(heroe.getPoder());
        heroeDTOP1.setImagen(heroe.getImagen());
        heroeDTOP1.setOrganizacionID(heroe.getOrganizacion().getId());
        return heroeDTOP1;
    }

    @Override
    public HeroeDTO updateHeroe(HeroeDTO heroeDTOP , Long id) {

        Heroe heroe = new Heroe();
        heroe.setId(id);
        heroe.setName(heroeDTOP.getName());
        heroe.setDescription(heroeDTOP.getDescription());
        heroe.setPoder(heroeDTOP.getPoder());
        heroe.setImagen(heroeDTOP.getImagen());

        Organizacion organizacion = organizacionRepository.getReferenceById(heroeDTOP.getOrganizacionID());
        heroe.setOrganizacion(organizacion);
        heroeRepository.save(heroe);

        HeroeDTO heroeDTOP1 = new HeroeDTO();
        heroeDTOP1.setName(heroe.getName());
        heroeDTOP1.setDescription(heroe.getDescription());
        heroeDTOP1.setPoder(heroe.getPoder());
        heroeDTOP1.setImagen(heroe.getImagen());
        heroeDTOP1.setOrganizacionID(heroe.getOrganizacion().getId());
        return heroeDTOP1;


    }

    @Override
    public Optional<Heroe> getHeroeByID(Long id) {
        return heroeRepository.findById(id);
    }

    @Override
    public void deleteHeroeById(Long id) {
        Heroe heroeExiste = this.getHeroeByID(id).orElseThrow();
        heroeRepository.delete(heroeExiste);
    }
}

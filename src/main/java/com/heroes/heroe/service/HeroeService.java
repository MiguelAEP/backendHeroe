package com.heroes.heroe.service;

import com.heroes.heroe.entidad.Heroe;
import com.heroes.heroe.entidad.HeroeDTO;

import java.util.List;
import java.util.Optional;

public interface HeroeService {

    public List<HeroeDTO> findAll();


    public HeroeDTO createHeroe(HeroeDTO heroeDTOP);

    public HeroeDTO updateHeroe(HeroeDTO heroeDTOP , Long id);

    public Optional<Heroe> getHeroeByID(Long id);

    void deleteHeroeById(Long id);


}

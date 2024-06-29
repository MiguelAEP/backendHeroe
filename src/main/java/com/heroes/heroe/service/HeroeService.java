package com.heroes.heroe.service;

import com.heroes.heroe.entidad.Heroe;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface HeroeService {

    public List<Heroe> findAll();
    public Heroe createHeroe(Heroe heroe);

    public Heroe updateHeroe(Heroe heroe);

    public Optional<Heroe> getHeroeByID(Long id);

    void deleteHeroeById(Long id);


}

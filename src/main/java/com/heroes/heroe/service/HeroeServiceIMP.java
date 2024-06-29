package com.heroes.heroe.service;

import com.heroes.heroe.entidad.Heroe;
import com.heroes.heroe.repository.HeroeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class HeroeServiceIMP implements HeroeService{

    @Autowired
    private HeroeRepository heroeRepository;

    @Override
    public List<Heroe> findAll() {
        return heroeRepository.findAll();
    }

    @Override
    public Heroe createHeroe(Heroe heroe) {
        return heroeRepository.save(heroe);
    }

    @Override
    public Heroe updateHeroe(Heroe heroe) {
        return heroeRepository.save(heroe);
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

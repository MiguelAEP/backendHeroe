package com.heroes.heroe.controller;

import com.heroes.heroe.entidad.Heroe;
import com.heroes.heroe.service.HeroeService;
import com.heroes.organizacion.entidad.Organizacion;
import com.heroes.organizacion.service.OrganizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/heroe")
public class HeroeController {

    @Autowired
    private HeroeService heroeService;

    @Autowired
    private OrganizacionService organizacionService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping
    public List<Heroe> findAll(){
        return heroeService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Heroe> saveHeroe(@RequestBody Heroe heroe ,
                                           @RequestParam("file") MultipartFile imagen)  {
        System.out.println("HOLA");
        try {
            byte[] imagenBytes= imagen.getBytes();
            System.out.println("imagenBytes"+imagenBytes);
        }catch (IOException e){
            e.getMessage();
        }
//imagen.transferTo(dest);

        return ResponseEntity.ok(heroe);
    }

}

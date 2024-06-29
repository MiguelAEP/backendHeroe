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
import java.util.Optional;

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

    @GetMapping("/{id}")
    public Heroe getHeroForID(@PathVariable Long id){
        Optional<Heroe> heroeSearched = heroeService.getHeroeByID(id);
        if(heroeSearched.isEmpty()){
            throw new RuntimeException("id no existe");
        }
        return heroeSearched.get();
    }

    @PostMapping("/createHeroe")
    public ResponseEntity<Heroe> createHeroe(@RequestParam("name") String name,
                                             @RequestParam("description") String description,
                                             @RequestParam("poder") String poder,
                                             @RequestParam("imagen") MultipartFile imagen,
                                             @RequestParam("organizacion") Long id   ) throws IOException {

        String fileName = imagen.getOriginalFilename();//muestra el nombre de la imagen
        System.out.println("fileName"+fileName);
        String filePath = uploadDir + File.separator + fileName; // aca se almacenara la imagen

        System.out.println("filePath"+filePath);
        File dest = new File(filePath); // crea un nuevo file y lo guarda a la ruta
        imagen.transferTo(dest);
        System.out.println("dest"+dest);

        Heroe heroe = new Heroe();
        heroe.setName(name);
        heroe.setDescription(description);
        heroe.setPoder(poder);
        heroe.setImagen("/uploads/" + fileName);

        Organizacion organizacionBuscado = organizacionService.getOrganizacionByID(id).orElseThrow();
        heroe.setOrganizacion(organizacionBuscado);

        Heroe savedHeroe = heroeService.createHeroe(heroe);
        return ResponseEntity.ok(savedHeroe);
    }



}

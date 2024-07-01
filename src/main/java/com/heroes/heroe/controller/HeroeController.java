package com.heroes.heroe.controller;

import com.heroes.heroe.entidad.Heroe;
import com.heroes.heroe.entidad.HeroeDTO;
import com.heroes.heroe.service.HeroeService;
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
    public List<HeroeDTO> findAll(){
        return heroeService.findAll();
    }

    @GetMapping("/{id}")
    public HeroeDTO getHeroForID(@PathVariable Long id){

        Optional<Heroe> heroeSearched = heroeService.getHeroeByID(id);

        if(heroeSearched.isEmpty()){
            throw new RuntimeException("id no existe");
        }

        HeroeDTO heroeDTOP = new HeroeDTO();
        heroeDTOP.setId(heroeSearched.get().getId());
        heroeDTOP.setName(heroeSearched.get().getName());
        heroeDTOP.setDescription(heroeSearched.get().getDescription());
        heroeDTOP.setPoder(heroeSearched.get().getPoder());
        heroeDTOP.setImagen(heroeSearched.get().getImagen());
        heroeDTOP.setOrganizacionID(heroeSearched.get().getOrganizacion().getId());

        System.out.println("heroeDTOP" + heroeDTOP);

        return heroeDTOP;
    }


    @PostMapping("/createHeroe")
    public ResponseEntity<HeroeDTO> createHeroe(@RequestParam("name") String name,
                                                @RequestParam("description") String description,
                                                @RequestParam("poder") String poder,
                                                @RequestParam("imagen") MultipartFile imagen,
                                                @RequestParam("organizacion") Long id   ) throws IOException, IOException {

        String fileName = imagen.getOriginalFilename();//muestra el nombre de la imagen
        System.out.println("fileName"+fileName);
        String filePath = uploadDir + File.separator + fileName; // aca se almacenara la imagen

        System.out.println("filePath"+filePath);
        File dest = new File(filePath); // crea un nuevo file y lo guarda a la ruta
        imagen.transferTo(dest);
        System.out.println("dest"+dest);

        HeroeDTO heroeDTOP = new HeroeDTO();
        heroeDTOP.setName(name);
        heroeDTOP.setDescription(description);
        heroeDTOP.setPoder(poder);
        heroeDTOP.setImagen("/uploads/" + fileName);
        heroeDTOP.setOrganizacionID(id);


        HeroeDTO savedHeroe = heroeService.createHeroe(heroeDTOP);
        return ResponseEntity.ok(savedHeroe);
    }

    @PutMapping("/updateHeroe/{id}")
    public ResponseEntity<HeroeDTO> updateHeroe(@RequestParam("name") String name,
                                             @RequestParam("description") String description,
                                             @RequestParam("poder") String poder,
                                             @RequestParam("imagen") MultipartFile imagen,
                                             @PathVariable Long id   ) throws IOException {

        String fileName = imagen.getOriginalFilename();//muestra el nombre de la imagen
        System.out.println("fileName"+fileName);
        String filePath = uploadDir + File.separator + fileName; // aca se almacenara la imagen

        System.out.println("filePath"+filePath);
        File dest = new File(filePath); // crea un nuevo file y lo guarda a la ruta
        imagen.transferTo(dest);

        System.out.println("dest"+dest);

        HeroeDTO heroeSearched = this.getHeroForID(id);

        heroeSearched.setName(name);
        heroeSearched.setDescription(description);
        heroeSearched.setPoder(poder);
        heroeSearched.setOrganizacionID(heroeSearched.getOrganizacionID());
        heroeSearched.setImagen("/uploads/" + fileName);

        HeroeDTO savedHeroe = heroeService.updateHeroe(heroeSearched , id);
        return ResponseEntity.ok(savedHeroe);


    }

}

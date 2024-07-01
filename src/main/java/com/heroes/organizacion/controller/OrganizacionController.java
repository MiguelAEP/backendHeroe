package com.heroes.organizacion.controller;

import com.heroes.heroe.entidad.HeroeDTO;
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
    @RequestMapping("/organizacion")
public class OrganizacionController {

    @Autowired
    private OrganizacionService organizacionService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping
    public List<Organizacion> findAll(){
        return organizacionService.findAll();
    }
    @GetMapping("/count")
    public Integer countOrganization(){
        return organizacionService.countOrganization();
    }

    @PostMapping
    public ResponseEntity<Organizacion> createOrganizacion(@RequestParam("name") String name,
                                                           @RequestParam("imagen") MultipartFile image) throws IOException {

        String fileName = image.getOriginalFilename();//muestra el nombre de la imagen
        System.out.println("fileName"+fileName);
        String filePath = uploadDir + File.separator + fileName; // aca se almacenara la imagen

        System.out.println("filePath"+filePath);
        File dest = new File(filePath); // crea un nuevo file y lo guarda a la ruta
        image.transferTo(dest);
        System.out.println("dest"+dest);

        Organizacion organizacion = new Organizacion();
        organizacion.setName(name);
        organizacion.setImagen("/uploads/" + fileName);

        if(this.countOrganization() >=2){
            throw new RuntimeException("Solo se puede agregar 2 organizaciones");
        }

        Organizacion savedOrg = organizacionService.createOrganizacion(organizacion);
        return ResponseEntity.ok(savedOrg);

    }
}

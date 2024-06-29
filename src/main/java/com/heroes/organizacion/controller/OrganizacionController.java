package com.heroes.organizacion.controller;

import com.heroes.organizacion.entidad.Organizacion;
import com.heroes.organizacion.service.OrganizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizacion")
public class OrganizacionController {

    @Autowired
    private OrganizacionService organizacionService;

    @GetMapping
    public List<Organizacion> findAll(){
        return organizacionService.findAll();
    }

    @PostMapping
    public ResponseEntity<Organizacion> createOrganizacion(@RequestBody Organizacion organizacion){
        return ResponseEntity.ok(organizacionService.createOrganizacion(organizacion));
    }
}

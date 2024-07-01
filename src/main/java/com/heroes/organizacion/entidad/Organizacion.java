package com.heroes.organizacion.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.heroes.heroe.entidad.Heroe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Organizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String imagen;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizacion")
    private List<Heroe> heroes = new ArrayList<>();
}

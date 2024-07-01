package com.heroes.heroe.entidad;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.heroes.organizacion.entidad.Organizacion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Heroe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String poder;

    private String imagen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizacion_id")
    private Organizacion organizacion;



}

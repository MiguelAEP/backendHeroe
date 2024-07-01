package com.heroes.heroe.entidad;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HeroeDTO {

    public Long id;
    public String name;
    public String description;
    public String poder;
    public String imagen;
    public Long organizacionID;


}

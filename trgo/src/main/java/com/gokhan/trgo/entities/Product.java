package com.gokhan.trgo.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pid;

    @ManyToOne
    @JoinColumn(name="lId",referencedColumnName = "lid")
    private Location location;

    @NotBlank(message = "Description name can not be blank")
    @Length(message = "Description must contain min 2 max", min = 2)
    private String description;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "products_taxonomy",
            joinColumns = @JoinColumn( name = "p_id", referencedColumnName = "pid"),
            inverseJoinColumns = @JoinColumn( name = "t_id", referencedColumnName = "tax_id")
    )
    private List<Taxonomy> taxonomy;

}

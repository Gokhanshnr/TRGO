package com.gokhan.trgo.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pid;

    @NotBlank(message = "Otel name can not be blank")
    @Length(message = "Otel name must contain min 2 max 50", min = 2, max=50)
    private String otel_name;

    @OneToMany
    @JoinTable(name="rooms",joinColumns = @JoinColumn(name="p_id" ),
            inverseJoinColumns = @JoinColumn( name = "t_id", referencedColumnName = "tax_id")
    )
    private List<Rooms> rooms;

    @NotBlank(message = "Description name can not be blank")
    @Length(message = "Description must contain min 2 max", min = 2)
    private String description;

    @ManyToOne
    @JoinColumn(name="lId",referencedColumnName = "lid")
    private Location location;



    @OneToMany(cascade ={CascadeType.MERGE})
    @JoinTable(name="products_taxonomy",joinColumns = @JoinColumn(name="p_id" ),
            inverseJoinColumns = @JoinColumn( name = "t_id", referencedColumnName = "tax_id")
    )
    private List<Taxonomy> taxonomies;


    @PositiveOrZero(message = "Star ratings must be between 0 5")
    @NotNull(message = "Star ratings must be between 0 5")
    @Max(value = 5, message = "Star ratings must be between 0 5")
    private Integer star_ratings;

}

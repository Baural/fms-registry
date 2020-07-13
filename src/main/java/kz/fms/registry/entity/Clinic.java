package kz.fms.registry.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author baur
 * @date on 01.07.2020
 */


@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Clinic {
    private Long id;
    private String name;
    private Region region;


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    @ManyToOne
    @JoinColumn(name = "region", referencedColumnName = "id")
    public Region getRegion() {
        return region;
    }

}

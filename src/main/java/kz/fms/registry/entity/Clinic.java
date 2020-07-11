package kz.fms.registry.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author baur
 * @date on 07.07.2020
 */


@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Clinic {
    private Integer id;
    private String name;
    private Region region;


    @Id
    @Column(name = "id")
    public Integer getId() {
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

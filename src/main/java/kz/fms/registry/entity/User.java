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
public class User {
    private Integer id;
    private String name;
    private String password;
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

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    @ManyToOne
    @JoinColumn(name = "region", referencedColumnName = "id")
    public Region getRegion() {
        return region;
    }

}

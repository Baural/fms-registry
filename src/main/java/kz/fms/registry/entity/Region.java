package kz.fms.registry.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author baur
 * @date on 07.07.2020
 */

@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Region {
    private Integer id;
    private String name;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    public String getName() {
        return name;
    }
}

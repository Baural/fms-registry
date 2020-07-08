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
public class Patient {
    private Integer id;
    private String lastname;
    private String firstname;
    private String middlename;
    private String iin;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    @Basic
    @Column(name = "middlename")
    public String getMiddlename() {
        return middlename;
    }

    @Basic
    @Column(name = "iin")
    public String getIin() {
        return iin;
    }

}

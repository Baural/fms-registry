package kz.fms.registry.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author baur
 * @date on 01.07.2020
 */

@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Patient {
    private Long id;
    private String lastname;
    private String firstname;
    private String middlename;
    private String iin;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public Long getId() {
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

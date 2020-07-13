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
public class Registry {
    private Long id;
    private Date publicationDate;
    private Date deadDate;
    private Patient patient;
    private Clinic clinic;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Basic
    @Column(name = "publication_date")
    public Date getPublicationDate() {
        return publicationDate;
    }

    @Basic
    @Column(name = "dead_date")
    public Date getDeadDate() {
        return deadDate;
    }

    @ManyToOne
    @JoinColumn(name = "patient", referencedColumnName = "id")
    public Patient getPatient() {
        return patient;
    }


    @ManyToOne
    @JoinColumn(name = "clinic", referencedColumnName = "id")
    public Clinic getClinic() {
        return clinic;
    }

}

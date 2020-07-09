package kz.fms.registry.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author baur
 * @date on 07.07.2020
 */


@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Registry {
    private Integer id;
    private Date publicationDate;
    private Date deadDate;
    private Patient patientId;
    private Clinic clinicId;

    @Id
    @Column(name = "id")
    public Integer getId() {
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
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    public Patient getPatientId() {
        return patientId;
    }


    @ManyToOne
    @JoinColumn(name = "clinic_id", referencedColumnName = "id")
    public Clinic getClinicId() {
        return clinicId;
    }

}

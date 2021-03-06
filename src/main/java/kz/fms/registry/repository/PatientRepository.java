package kz.fms.registry.repository;

import kz.fms.registry.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author baur
 * @date on 01.07.2020
 */

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT c FROM Patient c where " +
            "(:iin is null or :iin='' or c.iin like concat('%', :iin,'%'))  " +
            "order by c.iin asc")
    List<Patient> findByIin(@Param("iin") String iin);

    // получить все значения, сортировка по названию
    List<Patient> findAllByOrderByIinAsc();

}

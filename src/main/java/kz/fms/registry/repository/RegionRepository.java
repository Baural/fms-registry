package kz.fms.registry.repository;

import kz.fms.registry.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author baur
 * @date on 08.07.2020
 */

@Repository
public interface RegionRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT c FROM Patient c where " +
            "(:name is null or :name='' or upper(c.iin) like upper(concat('%', :name,'%')))  " +
            "order by c.iin asc")
    List<Patient> findByName(@Param("name") String name);
}

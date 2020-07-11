package kz.fms.registry.repository;

import kz.fms.registry.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author baur
 * @date on 09.07.2020
 */

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {

    @Query("SELECT c FROM Clinic c where " +
            "(:name is null or :name='' or UPPER(c.name) like upper(concat('%', :name,'%')))  " +
            "order by c.name asc")
    List<Clinic> findByName(@Param("name") String name);

}


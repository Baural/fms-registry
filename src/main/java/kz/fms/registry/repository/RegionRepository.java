package kz.fms.registry.repository;

import kz.fms.registry.entity.Patient;
import kz.fms.registry.entity.Region;
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
public interface RegionRepository extends JpaRepository<Region, Long> {

    @Query("SELECT c FROM Region c where " +
            "(:name is null or :name='' or upper(c.name) like upper(concat('%', :name,'%')))  " +
            "order by c.name asc")
    List<Region> findByName(@Param("name") String name);

}

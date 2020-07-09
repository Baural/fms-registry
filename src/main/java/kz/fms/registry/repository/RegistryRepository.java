package kz.fms.registry.repository;

import kz.fms.registry.entity.Registry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author baur
 * @date on 09.07.2020
 */

@Repository
public interface RegistryRepository extends JpaRepository<Registry, Long> {
    @Query("SELECT r FROM Registry r WHERE " +
            "(:patient is null or r.patient.id=:patient) and " +
            "(:clinic is null or r.clinic.id=:clinic)"
    )
    Page<Registry> findByParams(@Param("patient") Long patient,
                                   @Param("clinic") Long clinic,
                                   Pageable pageable
    );
}

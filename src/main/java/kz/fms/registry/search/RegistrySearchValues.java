package kz.fms.registry.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author baur
 * @date on 09.07.2020
 */


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrySearchValues {

    private Long patientId;
    private Long clinicId;

    // постраничность
    private Integer pageNumber;
    private Integer pageSize;

    // сортировка
    private String sortColumn;
    private String sortDirection;

}

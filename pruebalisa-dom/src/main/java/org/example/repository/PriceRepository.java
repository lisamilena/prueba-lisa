package org.example.repository;

import org.example.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query(value = "Select p.* " +
            "From prices p " +
            "Where (?1 = 0 or ?1 = p.brandid) and " +
            "(?2 = 0 or ?2 = p.productid) and " +
            "(?3 = 'null' or (parsedatetime(?3, 'yyyy-MM-dd') > p.startdate)) and " +
            "(?4 = 'null' or (parsedatetime(?4, 'yyyy-MM-dd') < p.enddate))" +
            "Order by p.id",
    nativeQuery = true)
    List<PriceEntity> findByParams(@Param("brandId") long brandId,
                                   @Param("productId") long productId,
                                   @Param("startDate") String startDate,
                                   @Param("endDate") String endDate);
}
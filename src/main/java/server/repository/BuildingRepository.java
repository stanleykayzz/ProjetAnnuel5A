package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.model.Building;

/**
 * Created by ileossa on 03/07/2017.
 */
@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer>{

    /***
     * Find and return one object Building if name equals param passed
     * @param nameBuild
     * @return
     */
    Building findBuildingByNameEquals(String nameBuild);
}

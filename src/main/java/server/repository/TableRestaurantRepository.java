package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.model.TableRestaurant;


/**
 * Created by ileossa on 05/04/2017.
 */
@Repository
public interface TableRestaurantRepository extends JpaRepository<TableRestaurant, Integer> {
}

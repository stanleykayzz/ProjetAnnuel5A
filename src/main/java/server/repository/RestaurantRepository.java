package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.model.Restaurant;


/**
 * Created by ileossa on 05/04/2017.
 */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    Restaurant findRestaurantByIdTable(int idTable);
    Restaurant findRestaurantByTokenClientEquals(String tokenClient);
}

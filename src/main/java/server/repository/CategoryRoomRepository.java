package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.model.CategoryRoom;
import server.model.Room;

import java.util.List;

/**
 * Created by ileossa on 02/07/2017.
 */
public interface CategoryRoomRepository extends JpaRepository<CategoryRoom, Integer> {

        /***
         * Find and return CategoryRoom object if equals by param passed
         * @param name
         * @return
         */
        CategoryRoom findCategoryRoomByName(String name);
}

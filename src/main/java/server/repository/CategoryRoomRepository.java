package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.model.CategoryRoom;
import server.model.Room;

import java.util.List;

/**
 * Created by ileossa on 02/07/2017.
 */
public interface CategoryRoomRepository extends JpaRepository<CategoryRoom, Long> {
        List<CategoryRoom> findAllByName(String name);
        CategoryRoom findCategoryRoomByName(String name);
}

package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.model.Booking;
import server.model.CategoryRoom;
import server.model.Room;

import java.util.List;

/**
 * Created by ileossa on 05/04/2017.
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    Room findByIdClient(int idClient);
    Room findByIdRoom(int idRoom);
    List<Room> findAllByIdRoom(int idRoom);
    List<Room> findAllByIdClient(int idClient);
    List<Room> findAllByCategoryRoom(CategoryRoom categoryRoom);
    List<Room> findAllByIdBuildingEquals(int idBuilding);
}

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

    /***
     * Return only one object room if == param
     * @param idRoom
     * @return
     */
    Room findById(int idRoom);
    Room findRoomByNumber(int numberRoom);

    /**
     *  Return all object if equald with param passed
     * @param idRoom
     * @return
     */
    List<Room> findAllById(int idRoom);
    List<Room> findAllByCategoryRoom(CategoryRoom categoryRoom);
    List<Room> findAllByIdBuildingEquals(int idBuilding);
}

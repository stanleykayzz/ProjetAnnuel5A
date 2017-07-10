package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.model.Booking;
import server.model.FestiveRoom;

/**
 * Created by ileossa on 05/04/2017.
 */
@Repository
public interface FestiveRoomRepository extends JpaRepository<FestiveRoom, Integer> {
    FestiveRoom findFestiveRoomById(int idFestiveRoom);
}

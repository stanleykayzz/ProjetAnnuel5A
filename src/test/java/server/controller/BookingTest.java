package server.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import server.repository.BookingRepository;

/**
 * Created by ileossa on 01/07/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class BookingTest {

    private BookingRepository bookingRepository;

    @Autowired
    public BookingTest(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Before
    public void setUp(){
/*
        Booking bk1 = new Booking();

        userRepository.saveAndFlush(user1);
        userRepository.saveAndFlush(user2);
        userRepository.saveAndFlush(user3);


        Room room = new Room("room1", user2);
        roomRepository.saveAndFlush(room);
*/
    }

    @Test
    public void should_create_new_booking(){

    }
}

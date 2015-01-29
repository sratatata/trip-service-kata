package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class TripServiceTest {

	@Test(expected = UserNotLoggedInException.class)
    public void throws_exception_if_not_logged(){
        TripService tripService = createSpyOfTripService(null);

        User user = new User();

        tripService.getTripsByUser(user);
    }

//    @Test
//    public void returns_empty_trip_list_if_no_friends(){
//        TripService tripService = createSpyOfTripService(new User());
//
//        User user = mock(User.class);
//
//        List<Trip> trips = tripService.getTripsByUser(user);
//
//        assertTrue(trips.isEmpty());
//    }



    @Test
    public void returns_empty_trip_list_if_chosen_user_has_no_friends(){
        TripService tripService = createSpyOfTripService(new User());

        User user = mock(User.class);
        List<User> friends = Collections.emptyList();
        when(user.getFriends()).thenReturn(friends);

        List<Trip> trips = tripService.getTripsByUser(user);

        assertTrue(trips.isEmpty());
    }

    @Test
    public void returns_empty_trip_list_if_logged_user_is_not_a_friend_of_chosen_user(){
        TripService tripService = createSpyOfTripService(new User());
        User user = mock(User.class);
        User friend = mock(User.class);
        List<User> friends = Arrays.asList(friend);
        when(user.getFriends()).thenReturn(friends);

        List<Trip> trips = tripService.getTripsByUser(user);

        assertTrue(trips.isEmpty());
    }

//    @Test
//    public void returns_trips_when_logged_user_is_a_friend_of_chosen_user(){
//        User loggedUser = new User();
//        TripService tripService = createSpyOfTripService(loggedUser);
//
//    }


    protected static TripService createSpyOfTripService(User toBeReturned) {
        TripService tripService = spy(new TripService());
        doReturn(toBeReturned).when(tripService).createLoggedUser();
        return tripService;
    }

}

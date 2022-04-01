package academy.mindswap.acceptance;

import academy.mindswap.commands.UserDto;
import academy.mindswap.mockdata.MockedData;
import academy.mindswap.persistence.models.Review;
import academy.mindswap.persistence.models.User;
import academy.mindswap.persistence.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test_getUserById_shouldReturn_success(){

        //Given

        User user= MockedData.getMockedUser();
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        String url = "/api/user/searchid/" + user.getUserId();

        //When
        ResponseEntity<UserDto>response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                UserDto.class);

        //Then

        verify(userRepository,times(1)).findById(user.getUserId());
        UserDto expected = MockedData.getMockedUserDto(user);
        assertEquals(expected, response.getBody());
    }

    /**
     * Test for getAllUsers
     */

    @Test
    public void test_getAllUsers_shouldReturn_success(){

        //Given

        List<User> userList = MockedData.getMockedUsers();
        when(userRepository.findAll()).thenReturn(userList);
        String url = "/api/user/allusers";

        //When
        ResponseEntity<List<UserDto>> response =restTemplate.exchange(
                url,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<UserDto>>() {
        });


        //Then
        verify(userRepository, times(1)).findAll();

        List<UserDto> expected = MockedData.getMockedUsersDto(userList);
        assertEquals(expected, response.getBody());

    }

    /**
     * Test for getUserReviews
     */
    //("/{id}/reviews")//ALL

    @Test
    public void test_getUserReviews_shouldReturn_success(){

        //Given
        User user = MockedData.getMockedUser();
        List<Review> reviewList = MockedData.getMockedUserReviews(user);
        when(reviewRepository.



        //When



        //Then


    }
}

package academy.mindswap.acceptance;


import academy.mindswap.commands.MovieDto;
import academy.mindswap.commands.UserDto;
import academy.mindswap.mockdata.MockedData;
import academy.mindswap.persistence.models.Movie;
import academy.mindswap.persistence.models.User;
import academy.mindswap.persistence.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test_get_user_by_id_should_return_success() {
        //Given

        User user = MockedData.getMockedUser();
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        String url = "/api/user/searchid/" + user.getUserId();

        //When
        ResponseEntity<UserDto> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                UserDto.class);

        //Then

        verify(userRepository,times(1)).findById(user.getUserId());
        UserDto expected = MockedData.getMockedUserDto(user);
        assertEquals(expected, response.getBody());





    }

}

package academy.mindswap.converters;

import academy.mindswap.commands.UserDto;
import academy.mindswap.persistence.models.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter {


    private final ModelMapper modelMapper;

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getUserId());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setReviews(user.getReviews());
        dto.setPassword(user.getPassword());
        return dto;
    }

    public User toEntity(UserDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setReviews(dto.getReviews());
        user.setPassword(dto.getPassword());
        return user;
    }
}

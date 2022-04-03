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
        UserDto dto = modelMapper.map(user, UserDto.class);
        dto.setNumberOfReviews(user.getReviews().size());
        dto.setPassword("*********");
        return dto;
    }

    public User toEntity(UserDto dto) {
        return modelMapper.map(dto, User.class);
    }
}

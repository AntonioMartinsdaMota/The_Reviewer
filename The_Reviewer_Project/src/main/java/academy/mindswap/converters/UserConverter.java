package academy.mindswap.converters;

import academy.mindswap.commands.UserDto;
import academy.mindswap.persistence.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    private ModelMapper modelMapper;

    public UserDto convertToDto(User user) {
       return modelMapper.map(user, UserDto.class);
    }

    public User convertToEntity (UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getUserId());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setReviews(user.getReviews());
        dto.setPassword("************");
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

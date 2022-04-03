package academy.mindswap.commands;

import academy.mindswap.persistence.models.Movie;
import academy.mindswap.persistence.models.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Integer id;

    @NotBlank( message= "Name cannot be empty")
    private String username;

    @Email(message = "Invalid email address")
    @NotBlank( message= "Email cannot be empty")
    private String email;

    private String password;

    private Integer numberOfReviews;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", email='" + email + '\'' +
                ", numberOfReviews=" + numberOfReviews +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && Objects.equals(email, userDto.email) && Objects.equals(password, userDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password);
    }
}

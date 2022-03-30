package academy.mindswap.commands;

import academy.mindswap.persistence.models.Movie;
import academy.mindswap.persistence.models.Review;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class UserDto {

    private Integer id;

    @NotBlank( message= "Name cannot be empty")
    private String username;

    @Email(message = "Invalid email address")
    @NotBlank( message= "Email cannot be empty")
    private String email;

    @NotNull
    @Size(min = 7)
    private String password;

    private Set<Review> reviews;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", email='" + email + '\'' +
                ", reviews=" + reviews +
                '}';
    }
}

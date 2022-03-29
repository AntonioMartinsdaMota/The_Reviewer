package academy.mindswap.lms.commands;

import lombok.*;
import org.springframework.jdbc.datasource.AbstractDriverBasedDataSource;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Integer id;

    @NotBlank( message= "Name cannot be empty")
    private String name;

    @Email(message = "Invalid email address")
    @NotBlank( message= "Email cannot be empty")
    private String email;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

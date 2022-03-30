package academy.mindswap.commands;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
public class LoginRequest {


    @Email
    @NotBlank
    private String email;
    @Size(min = 6)
    private String password;
}


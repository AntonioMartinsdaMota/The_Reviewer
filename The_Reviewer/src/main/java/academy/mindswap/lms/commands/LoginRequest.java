package academy.mindswap.lms.commands;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {


    @Email
    private String email;
    @Size(min=6)
    private String password;
}


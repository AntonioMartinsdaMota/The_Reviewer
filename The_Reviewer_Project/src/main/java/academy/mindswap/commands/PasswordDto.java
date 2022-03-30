package academy.mindswap.commands;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
public class PasswordDto {

    @NotBlank
    private Integer idUser;
    @NotBlank
    private String oldPassword;
    @Size(min = 7)
    private String newPassword;
    @Size(min = 7)
    private String newPasswordConfirm;
}

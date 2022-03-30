package academy.mindswap.commands;


import lombok.Data;

@Data
public class PasswordDto {

    private Integer idUser;
    private String oldPassword;
    private String newPassword;
    private String newPasswordConfirm;
}

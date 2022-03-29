package academy.mindswap.lms.commands;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PasswordDto {
    private Integer idUser;
    private String oldPassword;
    private String newPassword;
    private String newPasswordConfirm;
}

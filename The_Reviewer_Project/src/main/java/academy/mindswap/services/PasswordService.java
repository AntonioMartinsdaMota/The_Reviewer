package academy.mindswap.services;

import academy.mindswap.commands.PasswordDto;
import academy.mindswap.persistence.models.User;
import academy.mindswap.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    @Autowired
    UserRepository userRepository;


    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    private boolean validatePassword(String password) {
        return password.matches(PASSWORD_PATTERN);
    }

    private boolean isValidPassword(String password, String confirmPassword, String oldPassword, Integer userId) {
        User temp = new User();
        temp.setPassword("");
        return password.equals(confirmPassword)
                &&
                password.length() > 5
                &&
                userRepository.findById(userId)
                        .orElse(temp)
                        .getPassword()
                        .equals(oldPassword);
    }

    public boolean changePassword(PasswordDto passwordDto) {
        Integer userId = passwordDto.getIdUser();
        String password = passwordDto.getNewPassword();
        String confirmPassword = passwordDto.getNewPasswordConfirm();
        String oldPassword = passwordDto.getOldPassword();
        if (isValidPassword(password, confirmPassword, oldPassword, userId)) {
            User user = userRepository.findById(userId).orElse(null);
            if (user != null) {
                user.setPassword(password);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }


}

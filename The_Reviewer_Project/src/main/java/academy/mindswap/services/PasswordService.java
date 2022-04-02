package academy.mindswap.services;

import academy.mindswap.commands.PasswordDto;
import academy.mindswap.exceptions.badRequestExceptions.InvalidPasswordChangeRequestException;
import academy.mindswap.exceptions.badRequestExceptions.InvalidPasswordException;
import academy.mindswap.persistence.models.User;
import academy.mindswap.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class PasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;


    public void changePassword(PasswordDto passwordDto, HttpServletRequest request) {

        String userEmail = tokenService.getEmailFromToken(request);
        User user = userRepository.findByEmail(userEmail).get();

        String newPassword = passwordDto.getNewPassword();
        String confirmPassword = passwordDto.getNewPasswordConfirm();
        String oldPassword = passwordDto.getOldPassword();

        if (newPassword.length() < 7) {
            throw new InvalidPasswordException();
        }

        if (!newPassword.equals(confirmPassword)) {
            throw new InvalidPasswordChangeRequestException();
        }

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new InvalidPasswordChangeRequestException();
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }


}

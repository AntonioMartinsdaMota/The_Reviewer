package academy.mindswap.controllers;

import academy.mindswap.commands.PasswordDto;
import academy.mindswap.services.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@RequestMapping("/api/user")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @PutMapping("/changepassword")
    public ResponseEntity<String> changePassword(@RequestBody PasswordDto passwordDto, HttpServletRequest request) {

        passwordService.changePassword(passwordDto, request);
        return ResponseEntity.ok("Password changed successfully");
    }
}

package academy.mindswap.controllers;

import academy.mindswap.commands.PasswordDto;
import academy.mindswap.services.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/user")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @PutMapping("{id}/password")
    public ResponseEntity<String> changePassword(@PathVariable("id") Integer id, @RequestBody PasswordDto passwordDto) {

        if(!Objects.equals(id, passwordDto.getIdUser())) {
            return ResponseEntity.badRequest().body("Ids do not match");
        }
        if(passwordService.changePassword(passwordDto)) {
            return ResponseEntity.ok("Password changed successfully");
        }

        return ResponseEntity.unprocessableEntity().body("Password change failed");
    }
}

package academy.mindswap.lms.controllers;

import academy.mindswap.lms.commands.PasswordDto;
import academy.mindswap.lms.services.PasswordService;
import academy.mindswap.lms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/user")
public class PasswordController {

    //@Autowired
   // private UserService userService;
    @Autowired
    private PasswordService passwordService;



    @PutMapping("{id}/password")
    public ResponseEntity<String> changePassword(@PathVariable("id") Integer id, @RequestBody PasswordDto passwordDto) {
        if(!Objects.equals(id, passwordDto.getIdUser())) {
            return ResponseEntity.badRequest().body("Ids do not match");
        }
        if(passwordService.changePassword(passwordDto)){
            return ResponseEntity.ok("Password changed");
        };
        return ResponseEntity.unprocessableEntity().body("Password not changed");
    }

}



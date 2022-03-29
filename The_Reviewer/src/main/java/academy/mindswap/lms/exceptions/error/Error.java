package academy.mindswap.lms.exceptions.error;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Error {
    private String message;
    private String verb;
    private String path;
    private Date timestamp;
    private String statusCode;
}

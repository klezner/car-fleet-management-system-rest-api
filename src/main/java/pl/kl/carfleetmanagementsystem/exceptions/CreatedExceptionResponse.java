package pl.kl.carfleetmanagementsystem.exceptions;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CreatedExceptionResponse {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String trace;
    private String message;
    private String path;
}

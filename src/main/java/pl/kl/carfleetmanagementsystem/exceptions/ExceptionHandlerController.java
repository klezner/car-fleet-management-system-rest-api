package pl.kl.carfleetmanagementsystem.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleConstraintViolationException(ConstraintViolationException e) {
        log.warn(e.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNoSuchElementException(NoSuchElementException e) {
        log.warn(e.getMessage());
    }

    @ExceptionHandler(TripDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CreatedExceptionResponse> handleTripDateException(TripDateException e) {
        log.warn(e.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(CreatedExceptionResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .trace(e.getStackTrace().toString())
                        .message(e.getMessage())
                        .path("/trip")
                        .build());
    }

    @ExceptionHandler(TripMeterStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CreatedExceptionResponse> handleTripMeterStatusException(TripMeterStatusException e) {
        log.warn(e.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(CreatedExceptionResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .trace(e.getStackTrace().toString())
                        .message(e.getMessage())
                        .path("/trip")
                        .build());
    }

    @ExceptionHandler(RefuelingDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CreatedExceptionResponse> handleRefuelingDateException(RefuelingDateException e) {
        log.warn(e.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(CreatedExceptionResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .trace(e.getStackTrace().toString())
                        .message(e.getMessage())
                        .path("/refueling")
                        .build());
    }

    @ExceptionHandler(RefuelingMeterStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CreatedExceptionResponse> handleRefuelingMeterStatusException(RefuelingMeterStatusException e) {
        log.warn(e.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(CreatedExceptionResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .trace(e.getStackTrace().toString())
                        .message(e.getMessage())
                        .path("/refueling")
                        .build());
    }

    @ExceptionHandler(CarWithAssignedFleetCardException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CreatedExceptionResponse> handleCarWithAssignedFleetCardException(CarWithAssignedFleetCardException e) {
        log.warn(e.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(CreatedExceptionResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .trace(e.getStackTrace().toString())
                        .message(e.getMessage())
                        .path("/fleetcard")
                        .build());
    }

    @ExceptionHandler(FleetCardExpirationDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CreatedExceptionResponse> handleFleetCardExpirationDateException(FleetCardExpirationDateException e) {
        log.warn(e.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(CreatedExceptionResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .trace(e.getStackTrace().toString())
                        .message(e.getMessage())
                        .path("/fleetcard")
                        .build());
    }
}

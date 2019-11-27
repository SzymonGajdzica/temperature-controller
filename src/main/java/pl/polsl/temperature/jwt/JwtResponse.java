package pl.polsl.temperature.jwt;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
public class JwtResponse {

    @Getter(AccessLevel.PUBLIC)
    private final String token;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Getter(AccessLevel.PUBLIC)
    private final Date expirationDate;



}
package pl.polsl.temperature.jwt;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
public class AuthenticationView {

    @ApiModelProperty(required = true, example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYW5vIiwiZXhwIjoxNTc0OTQ1NDY3LCJpYXQiOjE1NzQ5Mjc0Njd9.1nVS9zoTiJ7ZRBLRsKwxf2rrcTxn6M6HfCRHNvnI5nC-52cvjtR0PiLMjU4XQaUkKPywttOi8OS6jeloHbQ8LA")
    @Getter(AccessLevel.PUBLIC)
    private final String token;

    @ApiModelProperty(required = true, example = "2019-11-27T10:57:43.019+01:00")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Getter(AccessLevel.PUBLIC)
    private final Date expirationDate;



}
package pl.polsl.temperature.jwt;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.polsl.temperature.base.Message;
import pl.polsl.temperature.exception.NotAuthorizedActionException;
import pl.polsl.temperature.exception.UsernameAlreadyUsedException;
import pl.polsl.temperature.exception.WrongBodyException;
import pl.polsl.temperature.user.User;
import pl.polsl.temperature.user.UserRepository;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping(value = "/authenticate")
public class JwtAuthenticationController {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils tokenUtils;
    private final JwtUserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Message registerUser(@RequestBody User user) {
        user.checkPostModel();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(userRepository.findByUsername(user.getUsername()).isPresent())
            throw new UsernameAlreadyUsedException(user.getUsername());
        userRepository.save(user);
        return new Message("User registered", "Now you can login with given username and password");
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JwtResponse createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        if(authenticationRequest.getUsername() == null || authenticationRequest.getPassword() == null)
            throw new WrongBodyException("user:password && username:password");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            String token = tokenUtils.generateToken(userDetails);
            return new JwtResponse(token, tokenUtils.getExpirationDateFromToken(token));
        }  catch (Exception e){
            throw new NotAuthorizedActionException("wrong credentials");
        }
    }



}
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
import pl.polsl.temperature.user.User;
import pl.polsl.temperature.user.UserPost;
import pl.polsl.temperature.user.UserRepository;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final AuthenticationUtils tokenUtils;
    private final AuthenticationUserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Message registerUser(@RequestBody UserPost userPost) {
        User user = new User();
        user.setUsername(userPost.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userPost.getPassword()));
        user.setEmail(userPost.getEmail());
        user.setSurname(userPost.getSurname());
        user.setName(userPost.getName());
        if(userRepository.findByUsername(user.getUsername()).isPresent())
            throw new UsernameAlreadyUsedException(user.getUsername());
        userRepository.save(user);
        return new Message("User registered", "Now you can login with given username and password");
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthenticationView createAuthenticationToken(@RequestBody AuthenticationPost authenticationPost) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationPost.getUsername(), authenticationPost.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationPost.getUsername());
            String token = tokenUtils.generateToken(userDetails);
            return new AuthenticationView(token, tokenUtils.getExpirationDateFromToken(token));
        }  catch (Exception e){
            throw new NotAuthorizedActionException("wrong credentials");
        }
    }



}
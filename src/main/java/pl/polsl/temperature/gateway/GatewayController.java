package pl.polsl.temperature.gateway;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.polsl.temperature.exception.NotFoundException;
import pl.polsl.temperature.token.TokenRepository;
import pl.polsl.temperature.user.User;

import java.util.Optional;

@RestController
@RequestMapping("/gateways")
@AllArgsConstructor
public class GatewayController {

    private final GatewayRepository gatewayRepository;
    private final TokenRepository tokenRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GatewayView addGateway(@RequestBody Gateway gateway, @RequestHeader("Authorization") String tokenHeader) {
        gateway.checkPostModel();
        User user = tokenRepository.getAndValidateUserFromHeader(tokenHeader, gateway.getUser().getId());
        gateway.setUser(user);
        return new GatewayView(gatewayRepository.save(gateway));
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteGateway(@PathVariable Long id, @RequestHeader("Authorization") String tokenHeader) {
        Optional<Gateway> gateway = gatewayRepository.findById(id);
        if (!gateway.isPresent())
            throw new NotFoundException(id);
        tokenRepository.validateUserWithHeader(tokenHeader, gateway.get().getUser());
        gatewayRepository.delete(gateway.get());
    }

}

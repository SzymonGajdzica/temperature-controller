package pl.polsl.temperature.gateway;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.polsl.temperature.exception.NotFoundException;
import pl.polsl.temperature.token.TokenRepository;

import java.util.Optional;

@RestController
@RequestMapping("/gateways")
@AllArgsConstructor
public class GatewayController {

    private final GatewayRepository gatewayRepository;
    private final TokenRepository tokenRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GatewayView addGateway(@RequestBody GatewayPost gatewayPost, @RequestHeader("Authorization") String tokenHeader) {
        Gateway gateway = new Gateway();
        gateway.setName(gatewayPost.getName());
        gateway.setUser(tokenRepository.getAndValidateUserFromHeader(tokenHeader, gatewayPost.getUserId()));
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

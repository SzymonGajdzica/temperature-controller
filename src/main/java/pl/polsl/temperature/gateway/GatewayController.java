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
    public GatewayView addGateway(@RequestBody GatewayPost gatewayPost, @RequestHeader(value = "Authorization", required = false) String tokenHeader) {
        Gateway gateway = new Gateway();
        gateway.setName(gatewayPost.getName());
        gateway.setUser(tokenRepository.getAndValidateUserFromHeader(tokenHeader, gatewayPost.getUserId()));
        return new GatewayView(gatewayRepository.save(gateway));
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteGateway(@PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String tokenHeader) {
        Gateway gateway = gatewayRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        tokenRepository.validateUserWithHeader(tokenHeader, gateway.getUser());
        gatewayRepository.delete(gateway);
    }

}

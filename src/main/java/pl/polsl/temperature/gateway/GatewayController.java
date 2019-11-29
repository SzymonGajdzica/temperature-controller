package pl.polsl.temperature.gateway;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.polsl.temperature.base.Message;
import pl.polsl.temperature.exception.NotFoundException;
import pl.polsl.temperature.token.TokenRepository;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Optional;

@RestController
@RequestMapping(value = "/gateways")
@AllArgsConstructor
public class GatewayController {

    private final GatewayRepository gatewayRepository;
    private final TokenRepository tokenRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GatewayView addGateway(@RequestBody GatewayPost gatewayPost, @ApiIgnore @RequestHeader(value = "Authorization") String tokenHeader) {
        Gateway gateway = new Gateway();
        gateway.setName(gatewayPost.getName());
        gateway.setUser(tokenRepository.getAndValidateUserFromHeader(tokenHeader, gatewayPost.getUserId()));
        return new GatewayView(gatewayRepository.save(gateway));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Message deleteGateway(@PathVariable Long id, @ApiIgnore @RequestHeader(value = "Authorization") String tokenHeader) {
        Gateway gateway = gatewayRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        tokenRepository.validateUserWithHeader(tokenHeader, gateway.getUser());
        gatewayRepository.delete(gateway);
        return new Message("Success", "Gateway deleted");
    }

}

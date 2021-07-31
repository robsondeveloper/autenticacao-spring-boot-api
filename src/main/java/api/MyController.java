package api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	Logger logger = LoggerFactory.getLogger(MyController.class);

	@GetMapping("/v1/admin")
	public String hello(JwtAuthenticationToken token) {
		logger.info("Authorities: {}", token.getToken().getClaimAsStringList("authorities"));
		return "Hello ".concat(token.getToken().getClaimAsString("name"));
	}

	@GetMapping("/v1/war")
	public String hello(@AuthenticationPrincipal Jwt jwt) {
		logger.info("Authorities: {}", jwt.getClaimAsStringList("authorities"));
		return "Hello ".concat(jwt.getClaimAsString("name"));
	}

}

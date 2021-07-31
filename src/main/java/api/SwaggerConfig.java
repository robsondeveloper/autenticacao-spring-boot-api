package api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	@Value("${springdoc.version}")
	String appVersion;

	private static final String SECURITY_SCHEME_NAME = "Bearer Token JWT";

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().addSecurityItem(appSecurityItem()).components(appComponents()).info(appInfo());
	}

	private SecurityRequirement appSecurityItem() {
		return new SecurityRequirement().addList(SECURITY_SCHEME_NAME);
	}

	private Components appComponents() {
		var securityScheme = new SecurityScheme();
		securityScheme.type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT");
		return new Components().addSecuritySchemes(SECURITY_SCHEME_NAME, securityScheme);
	}

	private Info appInfo() {
		var license = new License().name("Apache 2.0").url("http://springdoc.org");
		return new Info().title("MyApp API").version(appVersion).license(license);
	}

}

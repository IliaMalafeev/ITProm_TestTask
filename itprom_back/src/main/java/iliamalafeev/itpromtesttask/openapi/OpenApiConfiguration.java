package iliamalafeev.itpromtesttask.openapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(name = "Ilia Malafeev", email = "iliamalafeev1@gmail.com", url = "https://github.com/IliaMalafeev"),
                description = "OpenAPI documentation for ITPromTechTask backend application.",
                title = "OpenAPI specification - Ilia Malafeev",
                version = "1.0",
                license = @License(name = "Licence name and url here", url = "https://license-url-here.com"),
                termsOfService = "https://terms-url-here.com"
        ),
        servers = {
                @Server(description = "Local environment", url = "http://localhost:8080"),
                @Server(description = "Production environment", url = "https://production-url-here.com"),
        }
)
public class OpenApiConfiguration {
}

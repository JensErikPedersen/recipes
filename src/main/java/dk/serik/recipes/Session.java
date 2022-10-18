package dk.serik.recipes;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Data;

@RequestScope
@Component
@Data
public class Session {
	
	private String userName;
	
	private String sessionToken;
}

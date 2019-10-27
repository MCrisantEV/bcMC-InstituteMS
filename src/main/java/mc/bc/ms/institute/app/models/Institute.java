package mc.bc.ms.institute.app.models;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "institutes")
public class Institute {
	@Id
	private String id;

	@NotBlank
	private String institute;
}

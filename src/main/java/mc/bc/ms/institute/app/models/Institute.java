package mc.bc.ms.institute.app.models;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Institute {
	@Id
	private String id;
	private String intitute;
}

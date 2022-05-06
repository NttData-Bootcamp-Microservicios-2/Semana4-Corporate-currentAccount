package nttdata.bootcamp.microservicios.empresarial.cuentacorriente.documents.feign;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporateClient {
	
	private String id;
	
	private String corporatename;

	private String ruc;

	private String clientStatus;

	private List<String> listtypeCorporate;

	private List<String> businessRepreseentative;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;
}

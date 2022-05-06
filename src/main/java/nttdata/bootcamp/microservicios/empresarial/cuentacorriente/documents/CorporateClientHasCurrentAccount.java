package nttdata.bootcamp.microservicios.empresarial.cuentacorriente.documents;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nttdata.bootcamp.microservicios.empresarial.cuentacorriente.documents.feign.CorporateClient;
import nttdata.bootcamp.microservicios.empresarial.cuentacorriente.documents.feign.CurrentAccountPassiveAccount;

/*[2] Un cliente empresarial no puede tener una cuenta de ahorro o de plazo fijo,
pero sí múltiples cuentas corrientes*/

/*Cuenta corriente: posee comisión de mantenimiento y sin límite 
de movimientos mensuales*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "corporate-client-has-current-account")
public class CorporateClientHasCurrentAccount {
	@Id
	private String id;

	private String transactionName;

	private Double balance;

	private String status;

	private int currentmovements;

	List<CorporateClient> corporateClient;

	List<CurrentAccountPassiveAccount> currentPassiveAccount;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;
}

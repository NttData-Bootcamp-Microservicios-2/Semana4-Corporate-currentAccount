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
public class CurrentAccountPassiveAccount {
	
	private String id;
	
	// si es tipo cuenta persona o empresarial
	private String namebyaccount;

	// cantidad de comision por cuenta que tiene la cuenta corriente
	private Double savingrates;

	private boolean enabledtouse;

	private int dateofexpiry;

	private Double commissionformaintenance;

	private int maximummovementlimit;

	private Double balance;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;
}

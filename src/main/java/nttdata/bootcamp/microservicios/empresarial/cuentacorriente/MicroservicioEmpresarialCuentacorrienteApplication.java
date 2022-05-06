package nttdata.bootcamp.microservicios.empresarial.cuentacorriente;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;


@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class MicroservicioEmpresarialCuentacorrienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioEmpresarialCuentacorrienteApplication.class, args);
	}
	/*
	 * @Bean CommandLineRunner commandLineRunner(KafkaTemplate<String, String>
	 * kafkaTemplate) { return args ->{ kafkaTemplate.send(
	 * "corporateclientsgetcurrentaccounts",
	 * "Mensaje desde microservicio cliente juridico tiene cuenta corriente con apache KAFKA "
	 * ); }; }
	 */
}

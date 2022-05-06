package nttdata.bootcamp.microservicios.empresarial.cuentacorriente.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import nttdata.bootcamp.microservicios.empresarial.cuentacorriente.documents.CorporateClientHasCurrentAccount;

@Repository
public interface CorporateClientHasCurrentAccountRepository
		extends ReactiveMongoRepository<CorporateClientHasCurrentAccount, String> {

	
	
	
}

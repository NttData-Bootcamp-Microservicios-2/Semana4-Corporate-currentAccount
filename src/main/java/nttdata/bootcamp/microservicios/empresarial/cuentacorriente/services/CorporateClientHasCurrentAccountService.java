package nttdata.bootcamp.microservicios.empresarial.cuentacorriente.services;

import nttdata.bootcamp.microservicios.empresarial.cuentacorriente.documents.CorporateClientHasCurrentAccount;
import nttdata.bootcamp.microservicios.empresarial.cuentacorriente.documents.feign.CorporateClient;
import nttdata.bootcamp.microservicios.empresarial.cuentacorriente.documents.feign.CurrentAccountPassiveAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CorporateClientHasCurrentAccountService {

	public Mono<CorporateClientHasCurrentAccount> findById(String id);

	public Flux<CorporateClientHasCurrentAccount> findAlls();

	public Mono<CorporateClientHasCurrentAccount> saves(CorporateClientHasCurrentAccount document,
			CorporateClient corporateClient, CurrentAccountPassiveAccount currentAccount);

	public Mono<Void> delete(CorporateClientHasCurrentAccount document);

}

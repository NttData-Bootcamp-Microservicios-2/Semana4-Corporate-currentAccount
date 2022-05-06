package nttdata.bootcamp.microservicios.empresarial.cuentacorriente.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nttdata.bootcamp.microservicios.empresarial.cuentacorriente.documents.CorporateClientHasCurrentAccount;
import nttdata.bootcamp.microservicios.empresarial.cuentacorriente.documents.feign.CorporateClient;
import nttdata.bootcamp.microservicios.empresarial.cuentacorriente.documents.feign.CurrentAccountPassiveAccount;
import nttdata.bootcamp.microservicios.empresarial.cuentacorriente.services.CorporateClientHasCurrentAccountService;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;



@RestController
public class CorparateClientHasCurrentAccountController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CorparateClientHasCurrentAccountController.class);

	@Autowired
	CorporateClientHasCurrentAccountService service;

	
	@PostMapping("/create-corporate-has-current-account/{corporateId}/{currentaccountId}")
	public ResponseEntity<Mono<?>> newCorporateClienthasCurrentAccount(@PathVariable String corporateId,
			@PathVariable String currentaccountId,
			@Valid @RequestBody CorporateClientHasCurrentAccount corporatehasCurrentAccount,
			@Valid @RequestBody CorporateClient corporateClient,
			@Valid @RequestBody CurrentAccountPassiveAccount currentAccount) {

	
	Mono.just(corporateClient)
	.doOnNext(t->
	{
		corporateClient.setId(corporateId);
		

	}).onErrorReturn(corporateClient)
	.onErrorResume(e->Mono.just(corporateClient))
	.onErrorMap(f->new InterruptedException(f.getMessage()))
	.subscribe(x->LOGGER.info(x.toString()));
	
	
	
	
	Mono.just(currentAccount)
	.doOnNext(t->
	{
		
		currentAccount.setId(currentaccountId);

	}).onErrorReturn(currentAccount)
	.onErrorResume(e->Mono.just(currentAccount))
	.onErrorMap(f->new InterruptedException(f.getMessage()))
	.subscribe(x->LOGGER.info(x.toString()));
	
	
	
	Mono.just(corporatehasCurrentAccount)
	.doOnNext(t->
	{
		corporatehasCurrentAccount.setCreateAt(new Date());
		
	}).onErrorReturn(corporatehasCurrentAccount)
	.onErrorResume(e->Mono.just(corporatehasCurrentAccount))
	.onErrorMap(f->new InterruptedException(f.getMessage()))
	.subscribe(x->LOGGER.info(x.toString()));
	

	Mono<CorporateClientHasCurrentAccount> newCorporatehasCurrentAccount = 
			service.saves(corporatehasCurrentAccount, corporateClient,
					currentAccount);

	if(newCorporatehasCurrentAccount!=null)
	{

			return new ResponseEntity<>(newCorporatehasCurrentAccount, HttpStatus.CREATED);
		}

	return new ResponseEntity<>(Mono.just(new CorporateClientHasCurrentAccount()),HttpStatus.I_AM_A_TEAPOT);
}
	
	
	
	

}

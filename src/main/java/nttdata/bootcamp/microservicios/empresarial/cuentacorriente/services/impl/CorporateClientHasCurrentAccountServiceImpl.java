package nttdata.bootcamp.microservicios.empresarial.cuentacorriente.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import nttdata.bootcamp.microservicios.empresarial.cuentacorriente.documents.CorporateClientHasCurrentAccount;
import nttdata.bootcamp.microservicios.empresarial.cuentacorriente.documents.feign.CorporateClient;
import nttdata.bootcamp.microservicios.empresarial.cuentacorriente.documents.feign.CurrentAccountPassiveAccount;
import nttdata.bootcamp.microservicios.empresarial.cuentacorriente.repository.CorporateClientHasCurrentAccountRepository;
import nttdata.bootcamp.microservicios.empresarial.cuentacorriente.services.CorporateClientHasCurrentAccountService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CorporateClientHasCurrentAccountServiceImpl implements CorporateClientHasCurrentAccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CorporateClientHasCurrentAccountServiceImpl.class);
	
	   private final WebClient webClientCorporateClient;
	
	   private final WebClient webClientCurrentAccount;
	   
	   
	
	CorporateClientHasCurrentAccountRepository repository;
	
	
	
	public CorporateClientHasCurrentAccountServiceImpl(CorporateClientHasCurrentAccountRepository repository,@Value("${webclient.corporateclient.conexion}") String endpointCorporateclient,
			WebClient.Builder client1,
			@Value("${webclient.currentaccount.conexion}") String endpointCurrentAccount,
					WebClient.Builder client2) {
		
		this.repository =repository;
		
		this.webClientCorporateClient = client1.baseUrl(endpointCorporateclient).build();;
		this.webClientCurrentAccount = client2.baseUrl(endpointCurrentAccount).build();;
	}

	   private Mono<CorporateClient> findCorporateClientByIdentifier(String id) {
	        return webClientCorporateClient
	        		.get()
	        		.uri("/id/" + id)
	        		
	        		.retrieve()
	        		.bodyToMono(CorporateClient.class);
	        		//.share();
	    }

	    private Mono<CurrentAccountPassiveAccount> findCurrentAccountByIdentifier(String id) {
	        return webClientCurrentAccount
	        		.get()
	        		.uri("/id/" + id)
	        		.retrieve()
	        		.bodyToMono(CurrentAccountPassiveAccount.class);
	        		//.share();
	    }
	
	    
   
	@Override
	public Mono<CorporateClientHasCurrentAccount> findById(String id) {
	
		return repository.findById(id);
	}

	@Override
	public Flux<CorporateClientHasCurrentAccount> findAlls() {

		return repository.findAll();
	}

	@Override
	public Mono<CorporateClientHasCurrentAccount> saves(CorporateClientHasCurrentAccount document,
			CorporateClient corporateClient
			, CurrentAccountPassiveAccount currentAccount) {
	
		Mono<CorporateClient> client = findCorporateClientByIdentifier(corporateClient.getId())
				.doOnNext(x ->{
					List<CorporateClient> listcorporate = new ArrayList<>();
					listcorporate.add(x);
					document.setCorporateClient(listcorporate);
		});
		
		
		Mono<CurrentAccountPassiveAccount> current = findCurrentAccountByIdentifier(currentAccount.getId())
				.doOnNext(y ->{
					List<CurrentAccountPassiveAccount> listcurrent = new ArrayList<>();
					listcurrent.add(y);
					document.setCurrentPassiveAccount(listcurrent);
		});
		
		document.setCurrentmovements(0);
		document.setCreateAt(new Date());
		
		
		return repository.save(document);
	}

	@Override
	public Mono<Void> delete(CorporateClientHasCurrentAccount document) {
	
		return null;
	}

}

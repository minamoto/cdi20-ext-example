package org.tanoseam.examples.se;

//import org.tanoseam.examples.lib.LegacyLibrary;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class MyApplication {

	@Inject
	private HelloServiceImpl helloService;

	public MyApplication() {
		System.out.println("*** MyAppilcationBean constructor");
	}

	public void callBusinessMethod()  {
		System.out.println("*** MyAppilcationBean.callBusinessMethod");


		try {
			helloService.hello();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("**** MyApplication post construct");
	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("**** MyApplication pre destroy");
	}


}

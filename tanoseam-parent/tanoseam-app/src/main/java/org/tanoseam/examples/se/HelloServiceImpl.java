package org.tanoseam.examples.se;

import org.tanoseam.examples.extension.Retry;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;

@ApplicationScoped
public class

HelloServiceImpl implements HelloService {

	public HelloServiceImpl() {
		System.out.println("*** HelloServiceImpl constructor");
	}

	//@Retry(maxRetries = -1)
	@Retry(maxRetries = 5)
	public void hello() throws IOException {

		throw new IOException("Server Connection Error");
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("**** HelloServiceImpl post construct");
	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("**** HelloServiceImpl pre destroy");
	}

}

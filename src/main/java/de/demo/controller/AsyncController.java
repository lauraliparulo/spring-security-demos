package de.demo.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

public class AsyncController {

	@GetMapping("/bye")
	@Async //served by a different thread
	public void goodbye() {
		SecurityContext context = SecurityContextHolder.getContext();
		String username = context.getAuthentication().getName();
	}

	@GetMapping("/ciao")
	public String ciao() throws Exception {
		Callable<String> task = () -> {
			SecurityContext context = SecurityContextHolder.getContext();
			return context.getAuthentication().getName();
		};

		ExecutorService e = Executors.newCachedThreadPool();
		try {
			var contextTask = new DelegatingSecurityContextCallable<>(task);
			return "Ciao, " + e.submit(contextTask).get() + "!";
		} finally {
			e.shutdown();
		}
	}
}
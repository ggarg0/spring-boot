package com.demo.app.filter;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@Order(3)
@Component
public class LoggingFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) {
		System.out.println("init() method has been get invoked");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
			throws IOException, ServletException {

		System.out.println("getContentType in LoggingFilter : " + request.getContentType());
		filterchain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("destroy() method has been get invoked");
	}
}

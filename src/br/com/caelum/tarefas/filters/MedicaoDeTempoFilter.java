package br.com.caelum.tarefas.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class MedicaoDeTempoFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		long inicio = System.currentTimeMillis();
		
		chain.doFilter(req, res);
		
		long fim = System.currentTimeMillis();
		long duracao = fim - inicio;
		
		HttpServletRequest httpReq = (HttpServletRequest) req;
		String uri = httpReq.getRequestURI();
		String parametros = httpReq.getQueryString();
		String endereco = uri;
		if (parametros != null) {
			endereco += "?" + parametros;
		}
		
		System.out.println(endereco + " durou (ms): " + duracao);
	}

	@Override
	public void destroy() {
	}

}
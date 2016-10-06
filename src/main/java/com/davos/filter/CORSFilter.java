/**
 * 
 */
package com.davos.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author vigneshwaran.b
 *
 */
public class CORSFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(CORSFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "X-requested-with, Content-Type");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
			MDC.put("userName", authentication.getName());
		} else {
			MDC.put("userName", "Unknown");
		}
		MDC.put("ipAddress", req.getRemoteAddr());
		String pageName = ((HttpServletRequest) req).getRequestURI();
		String parameters = ((HttpServletRequest) req).getQueryString();
		if (!pageName.contains("/resources/") && !pageName.contains("/css/") && !pageName.contains("/js/")
				&& !pageName.contains("/fonts/") && !pageName.contains("/img/") && !pageName.contains("/vendors/")) {
			if (parameters != null) {
				logger.info(pageName + "?" + parameters);
			} else {
				logger.info(pageName);
			}

		}
		// logger.info(((HttpServletRequest) req).getRequestURI());

		chain.doFilter(req, res);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}

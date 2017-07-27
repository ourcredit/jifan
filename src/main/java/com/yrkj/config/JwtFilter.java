package com.yrkj.config;

import com.google.gson.Gson;
import com.yrkj.model.core.ActionResult;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		final String authHeader = request.getHeader("authorization");

		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(req, res);
		} else {

			if (authHeader == null || !authHeader.startsWith("Bearer ") || authHeader.length()<7) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=utf-8");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

				ActionResult result = new ActionResult(false,"无效token");
				result.setUnAuthorizedRequest(true);

				response.getWriter().write(new Gson().toJson(result));
				return;
			}

			final String token = authHeader.substring(7);

			try {
				final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
				request.setAttribute("claims", claims);
			} catch (final SignatureException e) {

				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=utf-8");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

				ActionResult result = new ActionResult(false,"无效token");
				result.setUnAuthorizedRequest(true);

				response.getWriter().write(new Gson().toJson(result));
				return;

			}

			chain.doFilter(req, res);
		}
	}
}

package com.dainc.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.dainc.model.MstUserModel;
import com.dainc.utils.SessionUtil;
import com.dainc.utils.JwTokenHelper;

public class AuthorizationFilter implements Filter {

    private ServletContext context;
	private static final String REALM = "gpcoder";
	private static final String AUTHENTICATION_SCHEME = "Bearer";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        if (url.startsWith("/training/admin")) {
            MstUserModel model = (MstUserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
            if (model != null) {
            	filterChain.doFilter(servletRequest, servletResponse); 

            } else {
                response.sendRedirect(request.getContextPath()+"/login?action=login&message=not_login&alert=danger");
            }
        } 
        
        else if (url.startsWith("/training/api")) {
    		// (1) Get Token Authorization from the header
    		String authorizationHeader = request.getHeader("Authorization");

    		// (2) Validate the Authorization header
    		if (!isTokenBasedAuthentication(authorizationHeader)) {
    			abortWithUnauthorized(response);
    			return;
    		}

    		// (3) Extract the token from the Authorization header
    		String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

    		try {

    			// (4) Validate the token
    			if (JwTokenHelper.isTokenExpired(token)) {
    				abortWithUnauthorized(response);
    				return;
    			}

    			// (5) Getting the User information from token
    			MstUserModel user = JwTokenHelper.getUserFromToken(token);

    			// (6) Overriding the security context of the current request
    			request.setAttribute("loginUser", user);
    			filterChain.doFilter(servletRequest, servletResponse); 
    		} catch (Exception e) {
    			abortWithUnauthorized(response);
    		}
        	
        }
        
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
    
	private boolean isTokenBasedAuthentication(String authorizationHeader) {

		// Check if the Authorization header is valid
		// It must not be null and must be prefixed with "Bearer" plus a whitespace
		// The authentication scheme comparison must be case-insensitive
		return authorizationHeader != null
				&& authorizationHeader.toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
	}

	private void abortWithUnauthorized(HttpServletResponse response) {

		// Abort the filter chain with a 401 status code response
		// The WWW-Authenticate header is sent along with the response
		PrintWriter out;
		try {
			out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print("token_flase");
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    @Override
    public void destroy() {

    }
}

package vn.edu.uit.realestate.Filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.NestedServletException;

public class AccessDeniedFilter extends GenericFilterBean {

@Override
public void doFilter(
        ServletRequest request,
        ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

    try {
        filterChain.doFilter(request, response);
    } catch (Exception e) {

        if (e instanceof NestedServletException &&
                ((NestedServletException) e).getRootCause() instanceof AccessDeniedException) {

            HttpServletRequest rq = (HttpServletRequest) request;
            HttpServletResponse rs = (HttpServletResponse) response;

            if (isAjax(rq)) {
                rs.sendError(HttpStatus.FORBIDDEN.value());
            } else {
                rs.sendRedirect("/");
            }
        }
    }
}

private Boolean isAjax(HttpServletRequest request) {
    return request.getContentType() != null &&
           request.getContentType().contains("application/json") &&
           request.getRequestURI() != null &&
           (request.getRequestURI().contains("api") || request.getRequestURI().contains("rest"));
    }
}
package br.com.logap.windfarm.filter;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(servletNames = { "Faces Servlet" })
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        String url = req.getRequestURI().toString();

        System.out.println(url);

        if (session.getAttribute("usuarioLogado") != null || url.endsWith("login.xhtml")
            || url.contains("javax.faces.resource/")) {

            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            res.sendRedirect(req.getServletContext().getContextPath() + "/login.xhtml");
        }
    }

    @Override
    public void destroy() {

    }
}

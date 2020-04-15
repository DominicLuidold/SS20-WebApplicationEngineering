/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.guestbook.servlet;

import at.fhv.dlu9576.guestbook.bean.HistoryBean;
import at.fhv.dlu9576.guestbook.bean.LoginBean;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;

@WebFilter("/*")
public class RequestHandler implements Filter {

    /*
     * Source: https://stackoverflow.com/questions/13274279/authentication-filter-and-servlet-for-login
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        String contextPath = request.getContextPath();
        boolean isStartPage = request.getRequestURI().equals(contextPath + "/")
                || request.getRequestURI().equals(contextPath + "/index.html")
                || request.getRequestURI().equals(contextPath + "/home.html");
        boolean isLoginRequest = request.getRequestURI().equals(contextPath + "/login.jsp")
                || request.getRequestURI().equals(contextPath + "/loginHandler");
        boolean isRegistrationRequest = request.getRequestURI().equals(contextPath + "/account.jsp")
                || request.getRequestURI().equals(contextPath + "/registrationHandler");
        boolean isResource = request.getRequestURI().contains("/css") || request.getRequestURI().contains("/img")
                || request.getRequestURI().contains("/js");

        /* Disable caching */
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");

        /* User not logged in */
        if (session != null) {
            LoginBean login = (LoginBean) session.getAttribute("login");
            boolean loggedIn = login != null && login.isValidLogin();
            if (loggedIn) {
                /* History */
                if (session.getAttribute("history") == null) {
                    LinkedList<HistoryBean> history = new LinkedList<>();
                    session.setAttribute("history", history);
                }
                if (!isResource) {
                    LinkedList<HistoryBean> history = (LinkedList<HistoryBean>) session.getAttribute("history");
                    HistoryBean historyItem = new HistoryBean();
                    historyItem.setTimeVisited(LocalDateTime.now());
                    historyItem.setUrl(request.getRequestURI());
                    if (request.getRequestURI().contains("home.html")) {
                        historyItem.setTitle("Home");
                    } else if (request.getRequestURI().contains("guestbook")) {
                        historyItem.setTitle("Gästebuch");
                    } else if (request.getRequestURI().contains("book")) {
                        historyItem.setTitle("Bücher");
                    } else if (request.getRequestURI().contains("newspaper")) {
                        historyItem.setTitle("Zeitungen");
                    } else if (request.getRequestURI().contains("audio")) {
                        historyItem.setTitle("Hörspiele");
                    } else if (request.getRequestURI().contains("account")) {
                        historyItem.setTitle("Account beantragen");
                    } else if (request.getRequestURI().contains("history.jsp")) {
                        historyItem.setTitle("Verlauf");
                    }
                    history.addFirst(historyItem);
                }
                filterChain.doFilter(request, response);
                return;
            }
        }

        /* Exceptions */
        if (isStartPage || isLoginRequest || isRegistrationRequest || isResource) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(contextPath + "/login.jsp");
        }
    }
}

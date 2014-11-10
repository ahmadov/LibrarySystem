package library;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author raminorujov
 */


public class LogFilter implements Filter {

    private static final boolean debug = true;
// The filter configuration object we are associated with. If
// this value is null, this filter instance is not currently
// configured.
    private FilterConfig filterConfig = null;

    public LogFilter() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        Throwable problem = null;
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession session = req.getSession();
            if (session.getAttribute("username") != null) {
                System.out.println("User " + session.getAttribute("username") + " is logged in, allow processing");
                chain.doFilter(request, response);
            } else {
                System.out.println("User is not logged in, redirect to login page");
                res.sendRedirect("login.jsp");
            }
        } catch (Throwable t) {
            problem = t;
            t.printStackTrace();
        }
    }

    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *     
* @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
}

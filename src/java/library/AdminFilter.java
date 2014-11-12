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
 * @author elmi
 */
public class AdminFilter implements Filter {
    
    private static final boolean debug = true;

    private FilterConfig filterConfig = null;
    
    public AdminFilter() {
    }   
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        Throwable problem = null;
        try {
            
            HttpServletResponse res = (HttpServletResponse) response;
            HttpServletRequest req = (HttpServletRequest) request; 
            HttpSession session = req.getSession();
            String[] accessed_IPs = filterConfig.getInitParameter("whitelist").split(",");
            boolean canAccessByTime = false , canAccessByIP = false;
            
            if (session.getAttribute("admin") == null || (Boolean)session.getAttribute("admin") == false)
                res.sendRedirect("index.jsp");
            
            String IP = req.getRemoteAddr();
            for (String accessed_IP : accessed_IPs) 
                if (IP.equals(accessed_IP)) {
                    canAccessByIP = true;
                    break;
                }
                
            if (canAccessByIP) {
               chain.doFilter(request, response);
            }
            else {
                res.sendRedirect("index.jsp");
            }
            
        } catch (Throwable t) {
            problem = t;
            t.printStackTrace();
        }
        
    }
    
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }
    
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
    
    public void destroy() {        
    }

    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
    }
}

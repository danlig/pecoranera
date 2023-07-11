package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter(filterName = "/AccessControlFilter", urlPatterns = "/*")
public class AccessControllFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	public AccessControllFilter() {
        super();
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		Boolean isAdmin = (Boolean) httpServletRequest.getSession().getAttribute("isAdmin");
		String path = httpServletRequest.getServletPath();
		
		boolean condition1 = path.contains("/common/") && isAdmin==null;
		boolean condition2 = path.contains("/admin/") && (isAdmin==null || !isAdmin);
		
		if (condition1 || condition2) {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsp");
			return;
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}
}

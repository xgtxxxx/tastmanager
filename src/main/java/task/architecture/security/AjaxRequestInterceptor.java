package task.architecture.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AjaxRequestInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory.getLogger(AjaxRequestInterceptor.class);

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String ajaxHeader = request.getHeader(CutomAuthenticationEntryPoint.XML_HTTP_REQUEST);
		if (isAjaxRequest(ajaxHeader)) {
			logger.debug("Ajax is blocked by authentication.");
			return false;
		}
		return true;
	}

	private boolean isAjaxRequest(String ajaxHeader) {
		ajaxHeader = StringUtils.trimToNull(ajaxHeader);
		if (ajaxHeader != null && ajaxHeader.equals(CutomAuthenticationEntryPoint.XML_HTTP_REQUEST_VALUE)) {
			return true;
		} else {
			return false;
		}
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}
}

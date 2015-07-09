package task.architecture.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * Provide authentication entry point for both regular and ajax http request
 * 
 * @author Roger Liu
 */
@SuppressWarnings("deprecation")
public class CutomAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
	private static final Log logger = LogFactory.getLog(CutomAuthenticationEntryPoint.class);
	public static final String XML_HTTP_REQUEST = "X-Requested-With";
	public static final String XML_HTTP_REQUEST_VALUE = "XMLHttpRequest";

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String redirectUrl = null;

		String url = request.getRequestURI();

		if (logger.isDebugEnabled()) {
			logger.debug("url:" + url);
		}

		// non client request
		// request.getHeader("client");
		if (!XML_HTTP_REQUEST_VALUE.equals(request.getHeader(XML_HTTP_REQUEST))) {

			if (this.isUseForward()) {

				if (this.isForceHttps() && "http".equals(request.getScheme())) {
					redirectUrl = buildHttpsRedirectUrlForRequest(httpRequest);
				}

				if (redirectUrl == null) {
					String loginForm = determineUrlToUseForThisRequest(httpRequest, httpResponse, authException);

					if (logger.isDebugEnabled()) {
						logger.debug("Server side forward to: " + loginForm);
					}

					RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginForm);

					dispatcher.forward(request, response);

					return;
				}
			} else {
				// redirect to login page. Use https if forceHttps true
				redirectUrl = buildRedirectUrlToLoginPage(httpRequest, httpResponse, authException);

			}

			redirectStrategy.sendRedirect(httpRequest, httpResponse, redirectUrl);
		} else {
			// Client request, android, ios, ajax
			if (logger.isDebugEnabled()) {
				logger.debug("This REQUEST is from an ajax request.");
			}

			ObjectMapper objectMapper = new ObjectMapper();
			response.setHeader("Content-Type", "application/json;charset=UTF-8");
			JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(response.getOutputStream(), JsonEncoding.UTF8);
			try {
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("success", false);
				result.put("msg", "No Authentication");

				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

				objectMapper.writeValue(jsonGenerator, result);
			} catch (JsonProcessingException ex) {
				throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
			}
		}
	}

}
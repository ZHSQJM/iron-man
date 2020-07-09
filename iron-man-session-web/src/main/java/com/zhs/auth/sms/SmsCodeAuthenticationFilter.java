package com.zhs.auth.sms;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/9 11:57
 * @package: com.zhs.auth
 * @description:
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_FORM_TELEPHONE_KEY = "telephone";

    private String telephoneParameter = "telephone";
    private boolean postOnly = true;

    public SmsCodeAuthenticationFilter() {
        super(new AntPathRequestMatcher("/smsLogin", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String telephone = this.obtainTelephone(request);

            if (telephone == null) {
                telephone = "";
            }

            telephone = telephone.trim();
            SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(telephone);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }


    protected String obtainTelephone(HttpServletRequest request) {
        return request.getParameter(this.telephoneParameter);
    }

    protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setTelephoneParameter(String telephoneParameter) {
        Assert.hasText(telephoneParameter, "Username parameter must not be empty or null");
        this.telephoneParameter = telephoneParameter;
    }



    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getTelephoneParameter() {
        return this.telephoneParameter;
    }

}
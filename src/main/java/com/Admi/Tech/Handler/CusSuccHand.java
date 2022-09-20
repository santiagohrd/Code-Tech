package com.Admi.Tech.Handler;



import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CusSuccHand extends SimpleUrlAuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();

    protected  void handler(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException{
        String targetUrl = determinaTargetUrl(authentication);
        if(response.isCommitted()){
            System.out.println("No sepuede redireccionar");
            redirectStrategy.sendRedirect(request, response, targetUrl);
        }
    }

    protected String determinaTargetUrl(Authentication authentication){
        String url="";
        Collection<? extends GrantedAuthority> authorities= authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();

        for (GrantedAuthority a: authorities){
            roles.add(a.getAuthority());
        }
        if(esAdmim(roles)){
            url="/VerEmpresas";
        }
        else if(esOper(roles)){
            url="/";
        }
        else {
            url="/Error";
        }
        return url;
    }



    private boolean esOper(List<String>roles){
        if (roles.contains("ROLE_USER")){
            return true;
        }
        return false;
    }
    private boolean esAdmim(List<String>roles){
        if (roles.contains("ROLE_ADMIN")){
            return true;
        }
        return false;
    }
}

package by.grodno.krivosheev.adminserver.configs;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final AdminServerProperties adminServer;

    public WebSecurityConfig(AdminServerProperties adminServer) {
        this.adminServer = adminServer;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        var successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(this.adminServer.getContextPath() + "/");

        http.authorizeRequests()
                    .antMatchers(this.adminServer.getContextPath() + "/assets/**").permitAll()
                    .antMatchers(this.adminServer.getContextPath() + "/login").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                        .loginPage(this.adminServer.getContextPath() + "/login")
                        .successHandler(successHandler)
                .and()
                    .logout()
                        .logoutUrl(this.adminServer.getContextPath() + "/logout")
                .and()
                    .httpBasic();
    }
}

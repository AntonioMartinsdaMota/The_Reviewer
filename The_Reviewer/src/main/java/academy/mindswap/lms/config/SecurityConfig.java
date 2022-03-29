package academy.mindswap.lms.config;


import academy.mindswap.lms.security.CookieFilter;
import academy.mindswap.lms.security.MyAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {



    private MyAuthProvider myAuthenticationProvider;

   /* @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers(HttpMethod.POST,"/auth/login");
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/auth/login").permitAll()
                .antMatchers(HttpMethod.GET,"/api/movies").permitAll()
               // .antMatchers(
          //      HttpMethod.GET,
          //      "/api/*/1","/login")
          //      .authenticated()
                //.permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new CookieFilter(myAuthenticationProvider), BasicAuthenticationFilter.class);

             //   .and()
        ;
      //  super.configure(http);
    }


    @Autowired
    public void setMyAuthenticationProvider(MyAuthProvider myAuthenticationProvider) {
        this.myAuthenticationProvider = myAuthenticationProvider;
    }
}

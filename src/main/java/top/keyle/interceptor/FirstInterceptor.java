package top.keyle.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.keyle.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class FirstInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        if (!(uri.contains("/mysshbook/user/login") || uri.contains("/mysshbook/user/regist")
                || uri.contains("/mysshbook/login") || uri.contains("/mysshbook/regist") || uri.contains("/mysshbook/user/captcha")))
        {
            if (session.getAttribute("currentUser") != null) {
                return true;
            } else {
                //request.getRequestDispatcher("/WEB-INF/action/user/login.html").forward(request, response);
                response.sendRedirect(request.getContextPath()+"/login");
            }
        }else{
            return true;
        }
        return false;
    }
}

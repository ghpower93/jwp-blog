package techcourse.myblog.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import techcourse.myblog.application.dto.UserResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (accessiblePathWithoutLogin(request)) {
            return true;
        }

        UserResponse userSession = (UserResponse) request.getSession().getAttribute("user");

        if (userSession == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }

    private boolean accessiblePathWithoutLogin(HttpServletRequest request) {
        return request.getRequestURI().equals("/users")
                && request.getMethod().equals("POST");
    }

    //TODO 로그인한 후 Login 경로로 접근 시 index 페이지로 이동
}

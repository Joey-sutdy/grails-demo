package demo.interceptor

import com.convertlab.constants.LogConstants
import com.convertlab.datapermission.CurrentUser
import com.convertlab.multitenancy.CurrentTenant
import org.slf4j.MDC
import org.springframework.beans.factory.annotation.Autowired

import javax.servlet.http.HttpServletRequest


class LoginInterceptor {

    int order = 100

    @Autowired
    CurrentTenant currentTenant


    LogInterceptor() {
        matchAll()
    }


    boolean before() {
        HttpServletRequest req = request as HttpServletRequest
        String x_request_id = req.getHeader(LogConstants.X_REQUEST_ID)
        if(!x_request_id){
            x_request_id = UUID.randomUUID().toString().replace("-", "")
        }
        MDC.put(LogConstants.X_REQUEST_ID, x_request_id)
        MDC.put(LogConstants.X_TENANT_ID, "${currentTenant.get()}")

        if (SecurityContextHolder.getContext().getAuthentication()?.principal instanceof CurrentUser) {
            def user = SecurityContextHolder.getContext().getAuthentication()?.principal as CurrentUser
            MDC.put(LogConstants.LOGIN_NAME, user.loginName)
            MDC.put(LogConstants.X_USER_ID, String.valueOf(user.id))
            MDC.put(LogConstants.X_USER_NAME, user.name)
        }

        def queryString = req.queryString
        if (queryString) {
            queryString = "?" + queryString
        } else {
            queryString = ""
        }

        String path = ""
        if (request.servletPath) {
            path += request.servletPath
        }
        if (request.contextPath) {
            path += request.contextPath
        }
        if (request.pathInfo) {
            path += request.pathInfo
        }

        log.info "==== request url: ${req.method} ${path}$queryString ===="
        return true
    }

    boolean after() {
        MDC.remove(LogConstants.X_REQUEST_ID)
        MDC.remove(LogConstants.X_TENANT_ID)
        MDC.remove(LogConstants.LOGIN_NAME)
        MDC.remove(LogConstants.X_USER_ID)
        MDC.remove(LogConstants.X_USER_NAME)

        return true
    }

    void afterView() {
        // no-op
    }
}

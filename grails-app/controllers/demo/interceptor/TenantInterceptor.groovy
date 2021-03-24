package demo.interceptor

import com.convertlab.constants.LogConstants
import com.convertlab.errors.CommonErrors
import grails.converters.JSON
import org.apache.commons.lang3.math.NumberUtils


class TenantInterceptor {

    boolean before() {
        def tenantParameter = params.x_tenant_id
        if(!tenantParameter){
            tenantParameter = request.getHeader(LogConstants.X_TENANT_ID)
        }
        if(!tenantParameter){
            return render(CommonErrors.ACCESS_DENIED.errorObject("x_tenant_id parameter is missing in header or parameter.") as JSON)
        }

        def tenantId = NumberUtils.toInt(tenantParameter, -1)

        currentTenant.set(tenantId)

        true
    }

    boolean after() {
        currentTenant.set(null)
        true
    }

    void afterView() {
        // no-op
    }
}

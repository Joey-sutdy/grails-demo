import com.convertlab.kafka.KafkaProducerService
import com.convertlab.multitenancy.CurrentTenantThreadLocal
import com.convertlab.multitenancy.resolver.ThreadLocalTenantResolver

// Place your Spring DSL code here


beans = {
    currentTenant(CurrentTenantThreadLocal)
    tenantResolver(ThreadLocalTenantResolver)
}

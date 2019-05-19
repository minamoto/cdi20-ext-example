package org.tanoseam.examples.extension;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.spi.*;
import javax.enterprise.util.AnnotationLiteral;
import java.util.List;

public class RetryExtension implements Extension {


    public void beforeBeanDiscovery(@Observes BeforeBeanDiscovery event, BeanManager bm) {
        log("BeforeBeanDiscovery");
        event.addAnnotatedType(RetryInterceptor.class, RetryInterceptor.class.getName());
    }


    public void afterTypeDiscovery(@Observes AfterTypeDiscovery event, BeanManager bm) {
        log("AfterTypeDiscovery");
        List<Class<?>> interceptors = event.getInterceptors();
        for(Class<?> interceptor: interceptors) {
            log("\t\tinterceptor=" + interceptor.getName());
        }
    }

    public <T> void processManagedBean(@Observes ProcessManagedBean<T> event) {
        log("processManagedBean=" + event.getAnnotatedBeanClass());
        AnnotatedType<?> annotatedType = event.getAnnotatedBeanClass();
        for (AnnotatedMethod<?> annotatedMethod : annotatedType.getMethods()) {
            Retry anno= annotatedMethod.getAnnotation(Retry.class);
            if (anno != null && anno.maxRetries() < 1) {
                event.addDefinitionError(new Exception("illegal maxRetries:" + anno.maxRetries()));
            }
        }
    }


    void log(String messages) {
        System.out.println("CDI Event: " + messages);
    }

}

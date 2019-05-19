package org.tanoseam.examples.extension;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.IOException;
import java.lang.annotation.Annotation;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@Retry
public class RetryInterceptor {

    @AroundInvoke
    public Object obj(InvocationContext ic)throws Exception {
        Retry ann = ic.getMethod().getAnnotation(Retry.class);
        int retryCount = ann.maxRetries();

        do {
            try {
                System.out.println("retryCount=" + retryCount);
                return ic.proceed();
            } catch (IOException ex) {
                System.out.println("IOException caught; retry ...");
            }
        } while (--retryCount > 0);

        throw new Error("Retry out exception");
    }
}
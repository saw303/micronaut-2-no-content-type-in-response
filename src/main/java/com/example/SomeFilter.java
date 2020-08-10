package com.example;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.OncePerRequestHttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Filter({"/**"})
public class SomeFilter extends OncePerRequestHttpServerFilter {

    private static final Logger log = LoggerFactory.getLogger(SomeFilter.class);


    @Override
    protected Publisher<MutableHttpResponse<?>> doFilterOnce(HttpRequest<?> request, ServerFilterChain chain) {
        return Flowable.fromPublisher(chain.proceed(request))
                .switchMap(response -> Flowable.fromCallable(
                        () -> {

                            if (response.getContentType().isEmpty()) {
                                log.error("This is unexpected since the content type was available in Micronaut 1.3.x");
                            }
                            return response;
                        }));
    }
}

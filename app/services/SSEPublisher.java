package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import play.libs.EventSource;
import play.libs.Json;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by ariel7342 on 8/2/2016.
 */
public class SSEPublisher implements Publisher<EventSource.Event> {
    List<Subscriber<? super EventSource.Event>> subscriberList = new ArrayList<>();
    @Inject
    public SSEPublisher(){
    }

    @Override
    public void subscribe(Subscriber<? super EventSource.Event> subscriber){
        subscriberList.add(subscriber);
    }

    public void publish(JsonNode nodeMessage, String id){
        for(Subscriber<? super EventSource.Event> subscriber : subscriberList){
            subscriber.onNext(new EventSource.Event(Json.stringify(nodeMessage), id, "message"));
        }
    }
}

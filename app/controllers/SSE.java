package controllers;

import akka.stream.javadsl.Source;
import com.google.inject.Inject;
import play.libs.EventSource;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.SSEPublisher;

/**
 * Created by ariel7342 on 8/2/2016.
 */
public class SSE extends Controller {
    @Inject
    SSEPublisher publisher;
    public Result send(String msg){
        publisher.publish(Json.toJson(msg), "moo");
        return ok();
    }

    public Result getStream(){
        final Source<EventSource.Event, ?> eventSource = Source.fromPublisher(publisher);
        return ok().chunked(eventSource.via(EventSource.flow())).as(Http.MimeTypes.EVENT_STREAM);
    }
}

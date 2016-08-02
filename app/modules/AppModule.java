package modules;

import play.api.Configuration;
import play.api.Environment;
import play.api.inject.Binding;
import play.api.inject.Module;
import scala.collection.Seq;
import services.SSEPublisher;

/**
 * Created by ariel7342 on 8/2/2016.
 */
public class AppModule extends Module {
    @Override
    public Seq<Binding<?>> bindings(Environment environment, Configuration configuration){
        return seq(bind(SSEPublisher.class).toSelf().eagerly());
    }
}

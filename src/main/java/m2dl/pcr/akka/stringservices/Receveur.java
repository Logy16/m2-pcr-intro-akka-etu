package m2dl.pcr.akka.stringservices;

import akka.actor.UntypedActor;
import akka.japi.Procedure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Receveur extends UntypedActor {

    public static final Logger log = LoggerFactory.getLogger(m2dl.pcr.akka.helloworld3.System.class);

    Procedure<Object> print = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof String) {
                log.info("Message received by Receveur: {}", msg);
            }
        }
    };

    @Override
    public void onReceive(Object msg) throws Exception {
        print.apply(msg);
    }
}

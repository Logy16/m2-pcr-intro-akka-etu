package m2dl.pcr.akka.stringservices;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.japi.Procedure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageSender extends UntypedActor {

    ActorRef nextActor;
    ActorRef finalActor;

    public static final Logger log = LoggerFactory.getLogger(m2dl.pcr.akka.helloworld3.System.class);

    public MessageSender(ActorRef nextActor, ActorRef finalActor) {
        this.nextActor = nextActor;
        this.finalActor = finalActor;
    }

    Procedure<Object> send = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof String) {
                String msgString = (String) msg;
                log.info("MiddleActor: " + msg);
                Letter letter = new Letter(msgString, finalActor);
                nextActor.tell(letter, getSelf());
                log.info("MiddleActor a envoyé le message");
            }
        }
    };

    @Override
    public void onReceive(Object msg) throws Exception {
        send.apply(msg);
    }
}

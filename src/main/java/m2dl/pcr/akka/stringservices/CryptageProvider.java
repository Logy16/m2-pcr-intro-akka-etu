package m2dl.pcr.akka.stringservices;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.japi.Procedure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CryptageProvider extends UntypedActor {

    public static final Logger log = LoggerFactory.getLogger(m2dl.pcr.akka.helloworld3.System.class);

    public CryptageProvider() {

    }

    Procedure<Object> crypting = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof Letter) {
                Letter msgLetter = (Letter) msg;
                log.info("Message non crypté : {}", msgLetter.getMsg());
                String msgCrypt = StringUtils.crypte(msgLetter.getMsg());
                log.info("Cryptage by Crypteur");
                msgLetter.getRecepter().tell(msgCrypt, getSelf());
            }
        }
    };

    @Override
    public void onReceive(Object msg) throws Exception {
        crypting.apply(msg);
    }
}

package m2dl.pcr.akka.stringservices;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.japi.Procedure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErreurControleProvider extends UntypedActor {

    public static final Logger log = LoggerFactory.getLogger(m2dl.pcr.akka.helloworld3.System.class);

    public ErreurControleProvider() {

    }

    Procedure<Object> addCtl = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof Letter) {
                Letter msgLetter = (Letter) msg;
                String msgCtl = StringUtils.ajouteCtrl(msgLetter.getMsg());
                log.info("Add ctl on message: {}", msgCtl);
                msgLetter.getRecepter().tell(msgCtl, getSelf());
            }
        }
    };

    @Override
    public void onReceive(Object msg) throws Exception {
        addCtl.apply(msg);
    }
}

package m2dl.pcr.akka.stringservices;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import m2dl.pcr.akka.eratosthene.FilterActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class System {

    public static final Logger log = LoggerFactory.getLogger(m2dl.pcr.akka.helloworld3.System.class);

    public static void main(String... args) throws Exception {

        final ActorSystem actorSystem = ActorSystem.create("actor-system");

        ActorRef cryptageProvider = actorSystem.actorOf(Props.create(CryptageProvider.class), "cryptage-actor");
        ActorRef erreurControleProvider = actorSystem.actorOf(Props.create(ErreurControleProvider.class), "erreur_ctl-actor");
        ActorRef receveur = actorSystem.actorOf(Props.create(Receveur.class), "receveur-actor");
        ActorRef messageSender = actorSystem.actorOf(Props.create(MessageSender.class, erreurControleProvider, receveur), "message_sender-actor");
        Letter message = new Letter("Salut un message", receveur);

        cryptageProvider.tell(message, null);
        Thread.sleep(1000);

        erreurControleProvider.tell(message, null);
        Thread.sleep(1000);

        Letter newMessage = new Letter("Salut un nouveau message", messageSender);
        cryptageProvider.tell(newMessage, null);
        Thread.sleep(1000);


        log.debug("Actor System Shutdown...");

        actorSystem.terminate();
    }
}

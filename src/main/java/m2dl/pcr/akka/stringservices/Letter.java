package m2dl.pcr.akka.stringservices;

import akka.actor.ActorRef;

public class Letter {

    private String msg;
    private ActorRef recepter;

    public Letter(String msg, ActorRef recepter) {
        this.msg = msg;
        this.recepter = recepter;
    }

    public String getMsg() {
        return msg;
    }

    public ActorRef getRecepter() {
        return recepter;
    }
}

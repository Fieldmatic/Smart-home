package com.bsep.smart.home.services.alarm;

import com.bsep.smart.home.model.facts.Alarm;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.DefaultAgendaEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlarmEventListener extends DefaultAgendaEventListener {

    private final NotifyAdminAboutAlarm notifyAdminAboutAlarm;

    @Autowired
    public AlarmEventListener(final NotifyAdminAboutAlarm notifyAdminAboutAlarm) {
        this.notifyAdminAboutAlarm = notifyAdminAboutAlarm;
    }

    @Override
    public void afterMatchFired(final AfterMatchFiredEvent event) {
        final Object matchedObject = event.getMatch().getObjects().get(0);
        if (matchedObject instanceof Alarm alarm) {
            System.out.println("Alarm Listener");
            switch (alarm.getAlarmType()) {
                case LOGIN_FAILED -> notifyAdminAboutAlarm.execute("Korisnik pokusao da se uloguje tri puta");
                case ERROR -> notifyAdminAboutAlarm.execute("Korisnik izazvao ERROR");
                case TOO_MANY_REQUEST -> notifyAdminAboutAlarm.execute("Korisnik napravio previse poziva");
            }
        }
    }
}

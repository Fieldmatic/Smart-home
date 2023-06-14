package com.bsep.smart.home.services.alarm;

import com.bsep.smart.home.model.facts.Alarm;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.DefaultAgendaEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlarmEventListener extends DefaultAgendaEventListener {

    private final NotifyAdminAboutAlarm notifyAdminAboutAlarm;
    private final NotifyUserAboutAlarm notifyUserAboutAlarm;
    private final SaveAlarm saveAlarm;

    @Autowired
    public AlarmEventListener(final NotifyAdminAboutAlarm notifyAdminAboutAlarm, final NotifyUserAboutAlarm notifyUserAboutAlarm, final SaveAlarm saveAlarm) {
        this.notifyAdminAboutAlarm = notifyAdminAboutAlarm;
        this.notifyUserAboutAlarm = notifyUserAboutAlarm;
        this.saveAlarm = saveAlarm;
    }

    @Override
    public void afterMatchFired(final AfterMatchFiredEvent event) {
        final Object matchedObject = event.getMatch().getObjects().get(0);
        if (matchedObject instanceof Alarm alarmFact) {
            System.out.println("Alarm Listener");
            saveAlarm.execute(alarmFact);
            switch (alarmFact.getAlarmType()) {
                case LOGIN_FAILED -> notifyAdminAboutAlarm.execute("The user tried to log in 3 times.");
                case ERROR -> notifyAdminAboutAlarm.execute("An error occurred.");
                case TOO_MANY_REQUEST -> notifyAdminAboutAlarm.execute("The user made too many requests.");
                case DEGREES -> notifyUserAboutAlarm.execute("High temperature on the thermometer.");
                case PRESSURE -> notifyAdminAboutAlarm.execute("High pressure on the barometer.");
                default -> {
                }
            }
        }
    }
}

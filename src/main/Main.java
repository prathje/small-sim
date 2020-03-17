package main;

import sim.Simulation;
import sim.component.RateLimiter;
import sim.event.RepeatingEvent;
import sim.event.EventExecutionContext;

public class Main {
    static final int TICKS_PER_SECOND = 20;
    public static void main(String[] args) {
        Simulation sim = new Simulation(1);

        sim.addComponent(new RateLimiter(1.0));


        sim.addEvent(new RepeatingEvent() {
            @Override
            protected void run(EventExecutionContext context) {
                System.out.println("Hello World! " + context.getCurMs() / 1000);
            }
        });

        sim.run(-1);
    }

}
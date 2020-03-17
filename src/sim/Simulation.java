package sim;

import sim.component.Component;
import sim.event.Event;
import sim.event.EventExecutionContext;
import sim.event.EventLoop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulation {

    final int ticksPerSecond;
    final double secondsPerTick;

    protected List<Component> components = new ArrayList<>();
    protected Map<String, Component> componentsByName = new HashMap<>();

    protected EventLoop eventLoop;

    public Simulation(int ticksPerSecond) {
        // TODO: Due to rounding, a small rounding error may lead to big gaps
        this.ticksPerSecond = ticksPerSecond;
        this.secondsPerTick = 1.0/this.ticksPerSecond;
        this.eventLoop = new EventLoop(new EventExecutionContext(this));
    }

    public synchronized void addComponent(Component component) {
        addComponent(component.getClass().getName(), component);
    }

    public synchronized void addComponent(String name, Component component) {
        this.components.add(component);
        this.componentsByName.put(name, component);
        component.setSimulation(this);
    }

    public synchronized Component getComponent(Class<?> c) {
        return componentsByName.get(c.getName());
    }

    public synchronized Component getComponent(String name) {
        return componentsByName.get(name);
    }

    public synchronized void addEvent(Event event) {
        this.eventLoop.addEvent(event);
    }

    public synchronized void tick() {

        for (Component c: components) {
            c.preTick(this.secondsPerTick);
        }

        this.eventLoop.step((long)(this.secondsPerTick*1000.0));

        for (Component c: components) {
            c.postTick(this.secondsPerTick);
        }
    }

    public synchronized void run(long limitMs) {
        while(limitMs == -1 || limitMs > (this.eventLoop.getCurMs() + this.ticksPerSecond)) {
            this.tick();
        }
    }

    public synchronized long getCurMs() {
        return this.eventLoop.getCurMs();
    }
}
package sim.event;

import sim.Simulation;

public class EventExecutionContext {
    long curMs = 0;
    
    Simulation simulation;

    public EventExecutionContext(Simulation simulation) {
        this.simulation = simulation;
    }

    public long getCurMs() {
        return this.curMs;
    }

    public Simulation getSimulation() {
        return this.simulation;
    }    
}
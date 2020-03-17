package sim.component;

import sim.Simulation;

public interface Component {
    void setSimulation(Simulation simulation);

    /**
     * You may add events in this callback
     * @param seconds the (fraction) of a second that is being simulated
     */
    void preTick(double seconds);

    /**
     * You may add events in this callback
     * @param seconds the (fraction) of a second that is being simulated
     */
    void postTick(double seconds);
}

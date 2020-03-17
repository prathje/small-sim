package sim.component;

import sim.Simulation;

public class RateLimiter implements Component {

    Double executionLimit = null;
    double simulatedDuration = 0;
    double realDuration = 0;


    long previousTime = 0;

    public RateLimiter(Double executionLimit) {
        this.setExecutionLimit(executionLimit);
    }

    public Double getExecutionLimit() {
        return executionLimit;
    }

    public void setExecutionLimit(Double executionLimit) {
        simulatedDuration = 0;
        realDuration = 0;
        this.executionLimit = executionLimit;
    }

    @Override
    public void setSimulation(Simulation simulation) {
        // not needed
    }

    @Override
    public void preTick(double seconds) {
        if (executionLimit == null) {
            return;
        }

        long now = System.currentTimeMillis();
        if (previousTime > 0) {

            // we check the wanted time
            realDuration += (now-previousTime) / 1000.0;
            double simulationLimit = realDuration*executionLimit;
            if(simulationLimit < simulatedDuration) {
                try {
                    double diff = (simulatedDuration-simulationLimit)*1000.0;
                    Thread.sleep((long)(diff/executionLimit));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        previousTime = now;
        simulatedDuration += seconds;
    }

    @Override
    public void postTick(double seconds) {

    }
}

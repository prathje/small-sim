package sim.event;

public abstract class RepeatingEvent implements Event {
    public boolean isDue(EventExecutionContext context) {
        return true;
    }

    protected abstract void run(EventExecutionContext context);

    public final void execute(EventExecutionContext context) {
        this.run(context);
        context.getSimulation().addEvent(this);        
    }
}
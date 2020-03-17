package sim.event;

public abstract class ScheduledEvent {
    long scheduledMs = 0;

    public boolean isDue(EventExecutionContext context) {
        return this.scheduledMs <= context.getCurMs();
    }

    public abstract void execute(EventExecutionContext context);
}
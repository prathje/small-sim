package sim.event;

public interface Event {
    boolean isDue(EventExecutionContext context);
    void execute(EventExecutionContext context);
}
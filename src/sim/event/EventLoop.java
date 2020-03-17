package sim.event;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class EventLoop {
    
    final EventExecutionContext eventExecutionContext;

    List<Event> events = new LinkedList<>();


    public EventLoop(EventExecutionContext eventExecutionContext) {
        this.eventExecutionContext = eventExecutionContext;
    }

    public synchronized void addEvent(Event event) {
        this.events.add(event);
    }

    public synchronized void step(long durationMs) {

        ListIterator<Event> iter = this.events.listIterator();

        List<Event> applicableEvents = new LinkedList<>();
      
        while(iter.hasNext()){
            Event e = iter.next();
            if (e.isDue(this.eventExecutionContext)) {
                applicableEvents.add(e);
                iter.remove();
            }
        }

        for(Event e: applicableEvents) {
            e.execute(this.eventExecutionContext);
        }

        this.eventExecutionContext.curMs += durationMs;  
    }

    public synchronized long getCurMs() {
        return this.eventExecutionContext.curMs;
    }
}
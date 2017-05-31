package drone;

import drone.Message;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.core.AgentTrait;
import io.sarl.lang.core.Capacity;

/**
 * @author Clément
 */
@SarlSpecification("0.5")
@SuppressWarnings("all")
public interface CommunicationCapacity extends Capacity {
  public abstract void sendMessage(final Message message);
  
  public abstract void startServer();
  
  public abstract void closeServer();
  
  public static class ContextAwareCapacityWrapper<C extends CommunicationCapacity> extends Capacity.ContextAwareCapacityWrapper<C> implements CommunicationCapacity {
    public ContextAwareCapacityWrapper(final C capacity, final AgentTrait caller) {
      super(capacity, caller);
    }
    
    public void sendMessage(final Message message) {
      try {
        ensureCallerInLocalThread();
        this.capacity.sendMessage(message);
      } finally {
        resetCallerInLocalThread();
      }
    }
    
    public void startServer() {
      try {
        ensureCallerInLocalThread();
        this.capacity.startServer();
      } finally {
        resetCallerInLocalThread();
      }
    }
    
    public void closeServer() {
      try {
        ensureCallerInLocalThread();
        this.capacity.closeServer();
      } finally {
        resetCallerInLocalThread();
      }
    }
  }
}

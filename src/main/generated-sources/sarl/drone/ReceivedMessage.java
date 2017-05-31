package drone;

import drone.Message;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.5")
@SuppressWarnings("all")
public class ReceivedMessage extends Event {
  public Message message;
  
  public ReceivedMessage(final Message o) {
    this.message = o;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ReceivedMessage other = (ReceivedMessage) obj;
    if (this.message == null) {
      if (other.message != null)
        return false;
    } else if (!this.message.equals(other.message))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.message== null) ? 0 : this.message.hashCode());
    return result;
  }
  
  /**
   * Returns a String representation of the ReceivedMessage event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected String attributesToString() {
    StringBuilder result = new StringBuilder(super.attributesToString());
    result.append("message  = ").append(this.message);
    return result.toString();
  }
  
  @SyntheticMember
  private final static long serialVersionUID = -277563188L;
}

package drone;

import drone.Message;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.5")
@SuppressWarnings("all")
public class SendMessage extends Event {
  public Message object;
  
  public SendMessage(final Message o) {
    this.object = o;
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
    SendMessage other = (SendMessage) obj;
    if (this.object == null) {
      if (other.object != null)
        return false;
    } else if (!this.object.equals(other.object))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.object== null) ? 0 : this.object.hashCode());
    return result;
  }
  
  /**
   * Returns a String representation of the SendMessage event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected String attributesToString() {
    StringBuilder result = new StringBuilder(super.attributesToString());
    result.append("object  = ").append(this.object);
    return result.toString();
  }
  
  @SyntheticMember
  private final static long serialVersionUID = -2255856636L;
}

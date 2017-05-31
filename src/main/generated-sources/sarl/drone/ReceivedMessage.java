package drone;

import drone.Message;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.5")
@SarlElementType(13)
@SuppressWarnings("all")
public class ReceivedMessage extends Event {
  public Message<Object> message;
  
  public ReceivedMessage(final Message<Object> o) {
    this.message = o;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
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
  private final static long serialVersionUID = -1104971092L;
}

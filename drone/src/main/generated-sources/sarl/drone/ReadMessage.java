package drone;

import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Event;

@SarlSpecification("0.5")
@SuppressWarnings("all")
public class ReadMessage extends Event {
  @SyntheticMember
  public ReadMessage() {
    super();
  }
  
  @SyntheticMember
  public ReadMessage(final Address source) {
    super(source);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 588368462L;
}

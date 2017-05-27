package drone;

import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Event;

@SarlSpecification("0.5")
@SuppressWarnings("all")
public class StopBuffer extends Event {
  @SyntheticMember
  public StopBuffer() {
    super();
  }
  
  @SyntheticMember
  public StopBuffer(final Address source) {
    super(source);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 588368462L;
}

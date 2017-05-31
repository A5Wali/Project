package drone;

import drone.TypeMessage;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class Message<T extends Object> {
  private TypeMessage type;
  
  private T message;
  
  public Message(final TypeMessage t, final T m) {
    this.type = t;
    this.message = m;
  }
  
  @Pure
  public TypeMessage getType() {
    return this.type;
  }
  
  public TypeMessage setType(final TypeMessage type) {
    return this.type = type;
  }
  
  @Pure
  public T getMessage() {
    return this.message;
  }
  
  public T setMessage(final T message) {
    return this.message = message;
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
}

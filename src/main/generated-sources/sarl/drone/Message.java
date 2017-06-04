package drone;

import drone.TypeMessage;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class Message {
  private TypeMessage type;
  
  private Object message;
  
  public Message(final TypeMessage t, final Object m) {
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
  public Object getMessage() {
    return this.message;
  }
  
  public Object setMessage(final Object message) {
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

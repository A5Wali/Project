package drone;

import drone.TypeMessage;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Objects;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class Message {
  private TypeMessage type;
  
  private Object message;
  
  private UUID UUID;
  
  public Message(final TypeMessage t, final Object m) {
    this.type = t;
    this.message = m;
  }
  
  public Message(final TypeMessage t, final Object m, final UUID uuid) {
    this.type = t;
    this.message = m;
    this.UUID = uuid;
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
  
  @Pure
  public UUID getUUID() {
    return this.UUID;
  }
  
  public UUID setUUID(final UUID uuid) {
    return this.UUID = uuid;
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
    Message other = (Message) obj;
    if (!Objects.equals(this.UUID, other.UUID)) {
      return false;
    }
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Objects.hashCode(this.UUID);
    return result;
  }
}

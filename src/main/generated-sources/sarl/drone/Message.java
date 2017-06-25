package drone;

import com.google.gson.Gson;
import drone.TypeMessage;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.5")
@SuppressWarnings("all")
public class Message {
  private TypeMessage type;
  
  private String message;
  
  private UUID UUID;
  
  public Message(final TypeMessage t, final String m) {
    this.type = t;
    this.message = m;
  }
  
  public Message(final TypeMessage t, final Object m) {
    Gson g = new Gson();
    this.type = t;
    this.message = g.toJson(m);
  }
  
  public Message(final TypeMessage t, final String m, final UUID uuid) {
    this.type = t;
    this.message = m;
    this.UUID = uuid;
  }
  
  public Message(final TypeMessage t, final Object m, final UUID uuid) {
    Gson g = new Gson();
    this.type = t;
    this.message = g.toJson(m);
    this.UUID = uuid;
  }
  
  @Pure
  public String toString() {
    return ((("Type : " + this.type) + ", Message : ") + this.message);
  }
  
  @Pure
  public TypeMessage getType() {
    return this.type;
  }
  
  public TypeMessage setType(final TypeMessage type) {
    return this.type = type;
  }
  
  @Pure
  public String getMessage() {
    return this.message;
  }
  
  public String setMessage(final String message) {
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
    if (this.type == null) {
      if (other.type != null)
        return false;
    } else if (!this.type.equals(other.type))
      return false;
    if (this.message == null) {
      if (other.message != null)
        return false;
    } else if (!this.message.equals(other.message))
      return false;
    if (this.UUID == null) {
      if (other.UUID != null)
        return false;
    } else if (!this.UUID.equals(other.UUID))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.type== null) ? 0 : this.type.hashCode());
    result = prime * result + ((this.message== null) ? 0 : this.message.hashCode());
    result = prime * result + ((this.UUID== null) ? 0 : this.UUID.hashCode());
    return result;
  }
}

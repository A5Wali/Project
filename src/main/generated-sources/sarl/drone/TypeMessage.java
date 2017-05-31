package drone;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;

/**
 * @author Clément
 */
@SarlSpecification("0.5")
@SarlElementType(10)
@SuppressWarnings("all")
public enum TypeMessage {
  ACC,
  
  SPAWN,
  
  DELETE;
}

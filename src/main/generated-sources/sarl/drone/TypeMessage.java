package drone;

import io.sarl.lang.annotation.SarlSpecification;

/**
 * @author Clément
 */
@SarlSpecification("0.5")
@SuppressWarnings("all")
public enum TypeMessage {
  ACC,
  
  SPAWN,
  
  DELETE,
  
  TARGET,
  
  CLOSE;
}

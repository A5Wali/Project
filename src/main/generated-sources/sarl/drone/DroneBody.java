package drone;

import drone.Cube;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Alexandre
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class DroneBody extends Cube {
  private Vector3f currentSpeed;
  
  private Vector3f currentAcc;
  
  private float maxSpeed;
  
  private float maxAcc;
  
  public DroneBody() {
    super();
    Vector3f _vector3f = new Vector3f();
    this.currentSpeed = _vector3f;
    Vector3f _vector3f_1 = new Vector3f();
    this.currentAcc = _vector3f_1;
    this.maxAcc = 5;
    this.maxSpeed = 20;
  }
  
  public DroneBody(final Point3f pos, final float w, final float maxS, final float maxA) {
    super(pos, w);
    Vector3f _vector3f = new Vector3f();
    this.currentSpeed = _vector3f;
    Vector3f _vector3f_1 = new Vector3f();
    this.currentAcc = _vector3f_1;
    this.maxSpeed = maxS;
    this.maxAcc = maxA;
  }
  
  public Vector3f setCurrentSpeed(final Vector3f cS) {
    return this.currentSpeed = cS;
  }
  
  public Vector3f setCurrentAcc(final Vector3f cA) {
    return this.currentAcc = cA;
  }
  
  @Pure
  public Vector3f getCurrentSpeed() {
    return this.currentSpeed;
  }
  
  @Pure
  public Vector3f getCurrentAcc() {
    return this.currentAcc;
  }
  
  @Pure
  public float getMaxSpeed() {
    return this.maxSpeed;
  }
  
  @Pure
  public float getMaxAcc() {
    return this.maxAcc;
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
    DroneBody other = (DroneBody) obj;
    if (Float.floatToIntBits(other.maxSpeed) != Float.floatToIntBits(this.maxSpeed))
      return false;
    if (Float.floatToIntBits(other.maxAcc) != Float.floatToIntBits(this.maxAcc))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Float.floatToIntBits(this.maxSpeed);
    result = prime * result + Float.floatToIntBits(this.maxAcc);
    return result;
  }
}

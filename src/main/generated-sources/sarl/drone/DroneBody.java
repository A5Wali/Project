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
  
  private Vector3f maxSpeed;
  
  private Vector3f maxAcc;
  
  private float breakZone;
  
  private float stopZone;
  
  private float protectingSphere;
  
  private final float tMax;
  
  public DroneBody() {
    super();
    Vector3f _vector3f = new Vector3f();
    this.currentSpeed = _vector3f;
    Vector3f _vector3f_1 = new Vector3f();
    this.maxAcc = _vector3f_1;
    Vector3f _vector3f_2 = new Vector3f();
    this.maxSpeed = _vector3f_2;
    this.tMax = 10;
    this.breakZone = 5;
    this.stopZone = 1;
    this.protectingSphere = 10;
  }
  
  public DroneBody(final Point3f pos, final float w, final Vector3f maxS, final Vector3f maxA, final float tMax, final float bZ, final float sZ, final float pS) {
    super(pos, w);
    Vector3f _vector3f = new Vector3f();
    this.currentSpeed = _vector3f;
    this.maxSpeed = maxS;
    this.maxAcc = maxA;
    this.tMax = tMax;
    this.breakZone = bZ;
    this.stopZone = sZ;
    this.protectingSphere = pS;
  }
  
  public Vector3f setCurrentSpeed(final Vector3f cS) {
    return this.currentSpeed = cS;
  }
  
  @Pure
  public Vector3f getCurrentSpeed() {
    return this.currentSpeed;
  }
  
  @Pure
  public Vector3f getMaxSpeed() {
    return this.maxSpeed;
  }
  
  @Pure
  public Vector3f getMaxAcc() {
    return this.maxAcc;
  }
  
  @Pure
  public float getTMax() {
    return this.tMax;
  }
  
  @Pure
  public float getBreakZone() {
    return this.breakZone;
  }
  
  @Pure
  public float getStopZone() {
    return this.stopZone;
  }
  
  @Pure
  public float getProtectingSphere() {
    return this.protectingSphere;
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
    if (Float.floatToIntBits(other.breakZone) != Float.floatToIntBits(this.breakZone))
      return false;
    if (Float.floatToIntBits(other.stopZone) != Float.floatToIntBits(this.stopZone))
      return false;
    if (Float.floatToIntBits(other.protectingSphere) != Float.floatToIntBits(this.protectingSphere))
      return false;
    if (Float.floatToIntBits(other.tMax) != Float.floatToIntBits(this.tMax))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Float.floatToIntBits(this.breakZone);
    result = prime * result + Float.floatToIntBits(this.stopZone);
    result = prime * result + Float.floatToIntBits(this.protectingSphere);
    result = prime * result + Float.floatToIntBits(this.tMax);
    return result;
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 5711524140L;
}

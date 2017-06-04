package drone;

import drone.DroneBody;
import drone.EnvObj;
import drone.Sphere;
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
public class Cube extends EnvObj {
  private float width;
  
  public Cube() {
    super();
    this.width = 0.0f;
  }
  
  public Cube(final Point3f pos, final float w) {
    super(pos);
    this.width = w;
  }
  
  public float setWidth(final float w) {
    return this.width = w;
  }
  
  @Pure
  public float getWidth() {
    return this.width;
  }
  
  public Vector3f computeForces(final DroneBody body, final Sphere target) {
    final float tMax = 10;
    Vector3f droneToObjectVector = null;
    Vector3f droneToTargetVector = null;
    droneToTargetVector.sub(target.getPosition(), body.getPosition());
    final float distanceDroneToTarget = droneToTargetVector.length();
    Vector3f newAcc = null;
    final float currentSpeed = body.getCurrentSpeed().length();
    float distanceOfObjectToPath = 0;
    Vector3f crossProduct = null;
    float distanceDroneToObject = 0;
    Vector3f objectToTargetVector = null;
    float timeToCollision = 0;
    Vector3f slidingForceH = null;
    Vector3f slidingForceV = null;
    Vector3f slidingForce = null;
    float _length = droneToTargetVector.length();
    final float scaledDroneToTargetVectorLength = (_length / 5);
    final float protectingSphere = 5;
    Vector3f repulsiveForce = null;
    droneToObjectVector.sub(this.getPosition(), body.getPosition());
    crossProduct.cross(droneToObjectVector, droneToTargetVector);
    float _length_1 = crossProduct.length();
    float _divide = (_length_1 / distanceDroneToTarget);
    distanceOfObjectToPath = _divide;
    distanceDroneToObject = droneToObjectVector.length();
    if ((distanceOfObjectToPath < ((this.width / 2) + (((Cube) body).width / 2)))) {
      distanceDroneToObject = droneToObjectVector.length();
      timeToCollision = (distanceDroneToObject / currentSpeed);
      objectToTargetVector.sub(target.getPosition(), this.getPosition());
      if ((((distanceDroneToObject < distanceDroneToTarget) && (objectToTargetVector.length() < distanceDroneToTarget)) && (timeToCollision < tMax))) {
        Vector3f _vector3f = new Vector3f(0, 1, 0);
        slidingForceH.cross(droneToObjectVector, _vector3f);
        float _dot = slidingForceH.dot(droneToTargetVector);
        boolean _lessThan = (_dot < 0);
        if (_lessThan) {
          slidingForceH.scale((-1));
        }
        Vector3f _vector3f_1 = new Vector3f(0, 0, 1);
        slidingForceV.cross(droneToObjectVector, _vector3f_1);
        float _dot_1 = slidingForceV.dot(droneToTargetVector);
        boolean _lessThan_1 = (_dot_1 < 0);
        if (_lessThan_1) {
          slidingForceV.scale((-1));
        }
        slidingForce.add(slidingForceV, slidingForceH);
        float _length_2 = slidingForce.length();
        float _divide_1 = (scaledDroneToTargetVectorLength / _length_2);
        slidingForce.scale(_divide_1);
        slidingForce.scale(((1 / timeToCollision) - (1 / tMax)));
        newAcc.add(slidingForce);
      }
    }
    if ((distanceDroneToObject < protectingSphere)) {
      repulsiveForce = droneToObjectVector;
      repulsiveForce.negate();
      float _length_3 = repulsiveForce.length();
      float _divide_2 = (scaledDroneToTargetVectorLength / _length_3);
      repulsiveForce.scale(_divide_2);
      repulsiveForce.scale(((1 / distanceDroneToObject) - (1 / protectingSphere)));
      newAcc.add(repulsiveForce);
    }
    return newAcc;
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
    Cube other = (Cube) obj;
    if (Float.floatToIntBits(other.width) != Float.floatToIntBits(this.width))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Float.floatToIntBits(this.width);
    return result;
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 1999852416L;
}

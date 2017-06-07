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
  
  @Override
  public Vector3f computeForces(final DroneBody body, final Sphere target) {
    final float tMax = body.getTMax();
    Vector3f droneToObjectVector = new Vector3f();
    Vector3f droneToTargetVector = new Vector3f();
    droneToTargetVector.sub(target.getPosition(), body.getPosition());
    final float distanceDroneToTarget = droneToTargetVector.length();
    Vector3f newAcc = new Vector3f();
    final float currentSpeed = body.getCurrentSpeed().length();
    float distanceOfObjectToPath = 0;
    Vector3f crossProduct = new Vector3f();
    float distanceDroneToObject = 0;
    Vector3f objectToTargetVector = new Vector3f();
    float timeToCollision = 0;
    Vector3f slidingForceH = new Vector3f();
    Vector3f slidingForceV = new Vector3f();
    Vector3f slidingForce = new Vector3f();
    final float protectingSphere = 10;
    Vector3f repulsiveForce = new Vector3f();
    final float halfSquare3 = 0.87f;
    final float objectRadius = ((this.width * halfSquare3) + ((Cube) body).width);
    droneToObjectVector.sub(this.getPosition(), body.getPosition());
    crossProduct.cross(droneToObjectVector, droneToTargetVector);
    float _length = crossProduct.length();
    float _divide = (_length / distanceDroneToTarget);
    distanceOfObjectToPath = _divide;
    distanceDroneToObject = droneToObjectVector.length();
    if ((distanceOfObjectToPath < objectRadius)) {
      distanceDroneToObject = droneToObjectVector.length();
      timeToCollision = (distanceDroneToObject / currentSpeed);
      objectToTargetVector.sub(target.getPosition(), this.getPosition());
      if ((((distanceDroneToObject < distanceDroneToTarget) && (objectToTargetVector.length() < distanceDroneToTarget)) && (timeToCollision < tMax))) {
        Vector3f _vector3f = new Vector3f(0, 1, 0);
        slidingForceH.cross(droneToObjectVector, _vector3f);
        float _dot = slidingForceH.dot(droneToTargetVector);
        boolean _lessThan = (_dot < 0);
        if (_lessThan) {
          slidingForceH.negate();
        }
        Vector3f _vector3f_1 = new Vector3f(0, 0, 1);
        slidingForceV.cross(droneToObjectVector, _vector3f_1);
        float _dot_1 = slidingForceV.dot(droneToTargetVector);
        boolean _lessThan_1 = (_dot_1 < 0);
        if (_lessThan_1) {
          slidingForceV.negate();
        }
        slidingForce.add(slidingForceV, slidingForceH);
        float _length_1 = slidingForce.length();
        float _divide_1 = (((objectRadius - distanceOfObjectToPath) * 5) / _length_1);
        slidingForce.scale(_divide_1);
        slidingForce.scale(((5 / timeToCollision) - (5 / tMax)));
        newAcc.add(slidingForce);
      }
    }
    if ((distanceDroneToObject < protectingSphere)) {
      repulsiveForce = droneToObjectVector;
      repulsiveForce.negate();
      float _length_2 = body.getCurrentSpeed().length();
      float _length_3 = repulsiveForce.length();
      float _divide_2 = (_length_2 / _length_3);
      repulsiveForce.scale(_divide_2);
      repulsiveForce.scale(((10 / distanceDroneToObject) - 1));
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

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
  public Vector3f computeForces(final DroneBody body, final Sphere target, final Vector3f droneToTargetVector, final float distanceDroneToTarget, final float currentSpeed) {
    Vector3f newAcc = new Vector3f();
    final float objectRadius = ((this.width * 1f) + (4.5f * ((Cube) body).width));
    Vector3f droneToObjectVector = new Vector3f();
    droneToObjectVector.sub(this.getPosition(), body.getPosition());
    final float distanceDroneToObject = droneToObjectVector.length();
    Vector3f crossProduct = new Vector3f();
    crossProduct.cross(droneToObjectVector, body.getCurrentSpeed());
    float _length = crossProduct.length();
    final float distanceOfObjectToPath = (_length / currentSpeed);
    if ((distanceOfObjectToPath < objectRadius)) {
      final float timeToCollision = ((distanceDroneToObject - objectRadius) / currentSpeed);
      Vector3f objectToTargetVector = new Vector3f();
      objectToTargetVector.sub(target.getPosition(), this.getPosition());
      if (((body.getCurrentSpeed().dot(droneToObjectVector) >= 0f) && (timeToCollision < body.getTMax()))) {
        Vector3f FirstPerpendicularVector = null;
        Vector3f SecondPerpendicularVector = new Vector3f();
        Vector3f slidingForce = new Vector3f();
        FirstPerpendicularVector = this.getPerpendicularVector(droneToObjectVector);
        FirstPerpendicularVector.normalize();
        SecondPerpendicularVector.cross(droneToObjectVector, FirstPerpendicularVector);
        SecondPerpendicularVector.normalize();
        float _dot = FirstPerpendicularVector.dot(droneToTargetVector);
        boolean _lessThan = (_dot < 0);
        if (_lessThan) {
          FirstPerpendicularVector.negate();
        }
        float _dot_1 = SecondPerpendicularVector.dot(droneToTargetVector);
        boolean _lessThan_1 = (_dot_1 < 0);
        if (_lessThan_1) {
          SecondPerpendicularVector.negate();
        }
        slidingForce.add(FirstPerpendicularVector, SecondPerpendicularVector);
        slidingForce.normalize();
        float _tMax = body.getTMax();
        float _multiply = (_tMax * (objectRadius - distanceOfObjectToPath));
        double _pow = Math.pow(_multiply, 1);
        double _pow_1 = Math.pow((timeToCollision * (distanceDroneToObject - objectRadius)), 1);
        float _divide = (((float) _pow) / ((float) _pow_1));
        float _multiply_1 = (_divide * 5f);
        slidingForce.scale(
          Math.abs(_multiply_1));
        newAcc.add(slidingForce);
      }
    }
    final float realDistanceDroneToObject = Math.max((distanceDroneToObject - (objectRadius - (4f * ((Cube) body).width))), 0.01f);
    float _protectingSphere = body.getProtectingSphere();
    boolean _lessThan_2 = (realDistanceDroneToObject < _protectingSphere);
    if (_lessThan_2) {
      Vector3f repulsiveForce = new Vector3f();
      repulsiveForce = droneToObjectVector;
      repulsiveForce.negate();
      repulsiveForce.normalize();
      double _pow_2 = Math.pow(body.getProtectingSphere(), 1);
      double _pow_3 = Math.pow(realDistanceDroneToObject, 1);
      float _divide_1 = (((float) _pow_2) / ((float) _pow_3));
      float _multiply_2 = (_divide_1 * 0.7f);
      repulsiveForce.scale(
        Math.abs(_multiply_2));
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
  private final static long serialVersionUID = -1564942304L;
}

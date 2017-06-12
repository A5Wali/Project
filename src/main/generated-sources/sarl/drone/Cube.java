package drone;

import drone.DroneBody;
import drone.EnvObj;
import drone.Sphere;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;
import org.eclipse.xtext.xbase.lib.InputOutput;
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
  
  @Pure
  public Vector3f getPerpendicularVector(final Vector3f original) {
    Vector3f _xblockexpression = null;
    {
      Vector3f C = null;
      if (((original.y != 0) || (original.z != 0))) {
        Vector3f _vector3f = new Vector3f(1, 0, 0);
        C = _vector3f;
      } else {
        Vector3f _vector3f_1 = new Vector3f(0, 1, 0);
        C = _vector3f_1;
      }
      Vector3f B = new Vector3f();
      B.cross(original, C);
      _xblockexpression = B;
    }
    return _xblockexpression;
  }
  
  @Override
  public Vector3f computeForces(final DroneBody body, final Sphere target) {
    Vector3f droneToTargetVector = new Vector3f();
    droneToTargetVector.sub(target.getPosition(), body.getPosition());
    final float distanceDroneToTarget = droneToTargetVector.length();
    Vector3f newAcc = new Vector3f();
    final float currentSpeed = body.getCurrentSpeed().length();
    final float objectRadius = ((this.width * 0.87f) + (1.8f * ((Cube) body).width));
    Vector3f droneToObjectVector = new Vector3f();
    droneToObjectVector.sub(this.getPosition(), body.getPosition());
    final float distanceDroneToObject = droneToObjectVector.length();
    Vector3f crossProduct = new Vector3f();
    crossProduct.cross(droneToObjectVector, droneToTargetVector);
    float _length = crossProduct.length();
    final float distanceOfObjectToPath = (_length / distanceDroneToTarget);
    if ((distanceOfObjectToPath < objectRadius)) {
      final float timeToCollision = ((distanceDroneToObject - objectRadius) / currentSpeed);
      Vector3f objectToTargetVector = new Vector3f();
      objectToTargetVector.sub(target.getPosition(), this.getPosition());
      if ((((distanceDroneToObject < distanceDroneToTarget) && (objectToTargetVector.length() < distanceDroneToTarget)) && (timeToCollision < body.getTMax()))) {
        Vector3f FirstPerpendicularVector = null;
        Vector3f SecondPerpendicularVector = new Vector3f();
        Vector3f slidingForce = new Vector3f();
        FirstPerpendicularVector = this.getPerpendicularVector(droneToObjectVector);
        FirstPerpendicularVector.normalize();
        SecondPerpendicularVector.cross(droneToObjectVector, FirstPerpendicularVector);
        SecondPerpendicularVector.normalize();
        InputOutput.<String>println("Sliding force");
        InputOutput.<String>println(("dist : " + Float.valueOf(distanceDroneToObject)));
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
        slidingForce.scale(
          Math.abs(
            ((((5 + objectRadius) - distanceOfObjectToPath) / (distanceDroneToObject - objectRadius)) * 0.35f)));
        newAcc.add(slidingForce);
      }
    }
    final float realDistanceDroneToObject = (distanceDroneToObject - objectRadius);
    float _protectingSphere = body.getProtectingSphere();
    boolean _lessThan_2 = (realDistanceDroneToObject < _protectingSphere);
    if (_lessThan_2) {
      InputOutput.<String>println("Repulsive force");
      Vector3f repulsiveForce = new Vector3f();
      repulsiveForce = droneToObjectVector;
      repulsiveForce.negate();
      repulsiveForce.normalize();
      double _pow = Math.pow(body.getProtectingSphere(), 4);
      double _pow_1 = Math.pow(realDistanceDroneToObject, 4);
      float _divide = (((float) _pow) / ((float) _pow_1));
      float _multiply = (_divide * 0.01f);
      repulsiveForce.scale(
        Math.abs(_multiply));
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
  private final static long serialVersionUID = 2044934913L;
}

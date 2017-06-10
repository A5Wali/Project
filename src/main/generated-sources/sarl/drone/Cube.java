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
  
  @Override
  public Vector3f computeForces(final DroneBody body, final Sphere target) {
    Vector3f droneToTargetVector = new Vector3f();
    droneToTargetVector.sub(target.getPosition(), body.getPosition());
    final float distanceDroneToTarget = droneToTargetVector.length();
    Vector3f newAcc = new Vector3f();
    final float currentSpeed = body.getCurrentSpeed().length();
    final float objectRadius = ((this.width * 0.87f) + ((Cube) body).width);
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
        Vector3f slidingForce1 = new Vector3f();
        Vector3f slidingForce2 = new Vector3f();
        InputOutput.<String>println("Sliding force");
        InputOutput.<String>println(("dist : " + Float.valueOf(distanceDroneToObject)));
        slidingForce1.cross(droneToTargetVector, droneToObjectVector);
        Vector3f slidingForce1ABS = null;
        slidingForce1ABS.absolute(slidingForce1);
        if (((slidingForce1ABS.x > slidingForce1ABS.y) && (slidingForce1ABS.x > slidingForce1ABS.z))) {
          if ((droneToObjectVector.x > 0)) {
            if ((slidingForce1.x > 0)) {
              slidingForce1.negate();
            }
          } else {
            if ((slidingForce1.x < 0)) {
              slidingForce1.negate();
            }
          }
        } else {
          if (((slidingForce1ABS.y > slidingForce1ABS.x) && (slidingForce1ABS.y > slidingForce1ABS.z))) {
            if ((droneToObjectVector.y > 0)) {
              if ((slidingForce1.y > 0)) {
                slidingForce1.negate();
              }
            } else {
              if ((slidingForce1.y < 0)) {
                slidingForce1.negate();
              }
            }
          } else {
            if (((slidingForce1ABS.z > slidingForce1ABS.x) && (slidingForce1ABS.z > slidingForce1ABS.y))) {
              if ((droneToObjectVector.z > 0)) {
                if ((slidingForce1.z > 0)) {
                  slidingForce1.negate();
                }
              } else {
                if ((slidingForce1.z < 0)) {
                  slidingForce1.negate();
                }
              }
            }
          }
        }
        float _length_1 = slidingForce1.length();
        float _divide = (1.0f / _length_1);
        slidingForce1.scale(_divide);
        slidingForce2.cross(droneToObjectVector, slidingForce1);
        float _dot = slidingForce2.dot(droneToTargetVector);
        boolean _lessThan = (_dot < 0);
        if (_lessThan) {
          slidingForce2.negate();
        }
        float _length_2 = slidingForce2.length();
        float _divide_1 = (1.0f / _length_2);
        slidingForce2.scale(_divide_1);
        slidingForce1.add(slidingForce2);
        float _tMax = body.getTMax();
        float _tMax_1 = body.getTMax();
        float _multiply = (_tMax * _tMax_1);
        float _divide_2 = (_multiply / timeToCollision);
        float _tMax_2 = body.getTMax();
        float _minus = (_divide_2 - _tMax_2);
        float _multiply_1 = (((objectRadius - distanceOfObjectToPath) * 5) * _minus);
        float _length_3 = slidingForce1.length();
        float _divide_3 = (_multiply_1 / _length_3);
        slidingForce1.scale(_divide_3);
        newAcc.add(slidingForce1);
      }
    }
    final float realDistanceDroneToObject = (distanceDroneToObject - objectRadius);
    float _protectingSphere = body.getProtectingSphere();
    boolean _lessThan_1 = (realDistanceDroneToObject < _protectingSphere);
    if (_lessThan_1) {
      Vector3f repulsiveForce = new Vector3f();
      repulsiveForce = droneToObjectVector;
      repulsiveForce.negate();
      float _protectingSphere_1 = body.getProtectingSphere();
      float _minus_1 = (_protectingSphere_1 - realDistanceDroneToObject);
      float _multiply_2 = (_minus_1 * 5);
      float _protectingSphere_2 = body.getProtectingSphere();
      float _protectingSphere_3 = body.getProtectingSphere();
      float _multiply_3 = (_protectingSphere_2 * _protectingSphere_3);
      float _divide_4 = (_multiply_3 / realDistanceDroneToObject);
      float _protectingSphere_4 = body.getProtectingSphere();
      float _minus_2 = (_divide_4 - _protectingSphere_4);
      float _multiply_4 = (_multiply_2 * _minus_2);
      float _length_4 = repulsiveForce.length();
      float _divide_5 = (_multiply_4 / _length_4);
      repulsiveForce.scale(_divide_5);
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

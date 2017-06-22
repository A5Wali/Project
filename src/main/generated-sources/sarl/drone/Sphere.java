package drone;

import drone.Cube;
import drone.DroneBody;
import drone.EnvObj;
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
@SuppressWarnings("all")
public class Sphere extends EnvObj {
  private float radius;
  
  public Sphere() {
    super();
    this.radius = 0.0f;
  }
  
  public Sphere(final Point3f pos, final float r) {
    super(pos);
    this.radius = r;
  }
  
  @Pure
  public float getRadius() {
    return this.radius;
  }
  
  @Override
  public Vector3f computeForces(final DroneBody body, final Sphere target, final Vector3f droneToTargetVector, final float distanceDroneToTarget, final float currentSpeed) {
    Vector3f newAcc = new Vector3f();
    float _width = ((Cube) body).getWidth();
    float _multiply = (3f * _width);
    final float objectRadius = ((this.radius * 1f) + _multiply);
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
        float _tMax = body.getTMax();
        float _multiply_1 = (_tMax * (objectRadius - distanceOfObjectToPath));
        double _pow = Math.pow(_multiply_1, 2);
        double _pow_1 = Math.pow((timeToCollision * (distanceDroneToObject - objectRadius)), 2);
        float _divide = (((float) _pow) / ((float) _pow_1));
        float _multiply_2 = (_divide * 
          0.1f);
        slidingForce.scale(
          Math.abs(_multiply_2));
        newAcc.add(slidingForce);
      }
    }
    float _width_1 = ((Cube) body).getWidth();
    float _multiply_3 = (2.5f * _width_1);
    float _minus = (objectRadius - _multiply_3);
    float _minus_1 = (distanceDroneToObject - _minus);
    final float realDistanceDroneToObject = Math.max(_minus_1, 0.01f);
    float _protectingSphere = body.getProtectingSphere();
    boolean _lessThan_2 = (realDistanceDroneToObject < _protectingSphere);
    if (_lessThan_2) {
      InputOutput.<String>println("Repulsive force");
      Vector3f repulsiveForce = new Vector3f();
      repulsiveForce = droneToObjectVector;
      repulsiveForce.negate();
      repulsiveForce.normalize();
      double _pow_2 = Math.pow(body.getProtectingSphere(), 1);
      double _pow_3 = Math.pow(realDistanceDroneToObject, 1);
      float _divide_1 = (((float) _pow_2) / ((float) _pow_3));
      float _multiply_4 = (_divide_1 * 0.3f);
      repulsiveForce.scale(
        Math.abs(_multiply_4));
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
    Sphere other = (Sphere) obj;
    if (Float.floatToIntBits(other.radius) != Float.floatToIntBits(this.radius))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + Float.floatToIntBits(this.radius);
    return result;
  }
  
  @SyntheticMember
  private final static long serialVersionUID = -940656836L;
}

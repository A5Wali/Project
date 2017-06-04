package drone;

import drone.Cube;
import drone.DroneBody;
import drone.Moving;
import drone.Sphere;
import io.sarl.core.Logging;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.Skill;
import io.sarl.lang.util.ClearableReference;
import java.util.List;
import javax.vecmath.Vector3f;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Alexandre
 */
@SarlSpecification("0.5")
@SarlElementType(19)
@SuppressWarnings("all")
public class SeekingSkill extends Skill implements Moving {
  public void install() {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("Installing the skill");
  }
  
  public void uninstall() {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("Uninstalling the skill");
  }
  
  @Pure
  public Vector3f seekingFixedTarget(final List<Cube> listOfObstacle, final Sphere target) {
    Cube _get = listOfObstacle.get(0);
    final DroneBody body = ((DroneBody) _get);
    final float tMax = 10;
    final float breakZone = 5;
    final float stopZone = 1;
    Vector3f droneToTargetVector = null;
    droneToTargetVector.sub(target.getPosition(), body.getPosition());
    final float distanceDroneToTarget = droneToTargetVector.length();
    if ((distanceDroneToTarget <= stopZone)) {
      Vector3f stopVector = body.getCurrentSpeed();
      stopVector.negate();
      return stopVector;
    } else {
      Vector3f droneToObjectVector = null;
      Vector3f newAcc = droneToTargetVector;
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
      for (int i = 1; (i < ((Object[])Conversions.unwrapArray(listOfObstacle, Object.class)).length); i++) {
        {
          droneToObjectVector.sub(listOfObstacle.get(i).getPosition(), body.getPosition());
          crossProduct.cross(droneToObjectVector, droneToTargetVector);
          float _length_1 = crossProduct.length();
          float _divide = (_length_1 / distanceDroneToTarget);
          distanceOfObjectToPath = _divide;
          distanceDroneToObject = droneToObjectVector.length();
          float _width = listOfObstacle.get(i).getWidth();
          float _divide_1 = (_width / 2);
          float _width_1 = body.getWidth();
          float _divide_2 = (_width_1 / 2);
          float _plus = (_divide_1 + _divide_2);
          boolean _lessThan = (distanceOfObjectToPath < _plus);
          if (_lessThan) {
            distanceDroneToObject = droneToObjectVector.length();
            timeToCollision = (distanceDroneToObject / currentSpeed);
            objectToTargetVector.sub(target.getPosition(), listOfObstacle.get(i).getPosition());
            if ((((distanceDroneToObject < distanceDroneToTarget) && (objectToTargetVector.length() < distanceDroneToTarget)) && (timeToCollision < tMax))) {
              Vector3f _vector3f = new Vector3f(0, 1, 0);
              slidingForceH.cross(droneToObjectVector, _vector3f);
              float _dot = slidingForceH.dot(droneToTargetVector);
              boolean _lessThan_1 = (_dot < 0);
              if (_lessThan_1) {
                slidingForceH.scale((-1));
              }
              Vector3f _vector3f_1 = new Vector3f(0, 0, 1);
              slidingForceV.cross(droneToObjectVector, _vector3f_1);
              float _dot_1 = slidingForceV.dot(droneToTargetVector);
              boolean _lessThan_2 = (_dot_1 < 0);
              if (_lessThan_2) {
                slidingForceV.scale((-1));
              }
              slidingForce.add(slidingForceV, slidingForceH);
              float _length_2 = slidingForce.length();
              float _divide_3 = (scaledDroneToTargetVectorLength / _length_2);
              slidingForce.scale(_divide_3);
              slidingForce.scale(((1 / timeToCollision) - (1 / tMax)));
              newAcc.add(slidingForce);
            }
          }
          if ((distanceDroneToObject < protectingSphere)) {
            repulsiveForce = droneToObjectVector;
            repulsiveForce.negate();
            float _length_3 = repulsiveForce.length();
            float _divide_4 = (scaledDroneToTargetVectorLength / _length_3);
            repulsiveForce.scale(_divide_4);
            repulsiveForce.scale(((1 / distanceDroneToObject) - (1 / protectingSphere)));
            newAcc.add(repulsiveForce);
          }
        }
      }
      if ((distanceDroneToTarget > breakZone)) {
        float _maxSpeed = body.getMaxSpeed();
        float _length_1 = newAcc.length();
        float _divide = (_maxSpeed / _length_1);
        newAcc.scale(_divide);
      } else {
        float _maxSpeed_1 = body.getMaxSpeed();
        float _multiply = (_maxSpeed_1 * distanceDroneToTarget);
        float _divide_1 = (_multiply / breakZone);
        float _length_2 = newAcc.length();
        float _divide_2 = (_divide_1 / _length_2);
        newAcc.scale(_divide_2);
      }
      newAcc.sub(body.getCurrentSpeed());
      float _length_3 = newAcc.length();
      float _maxAcc = body.getMaxAcc();
      boolean _greaterThan = (_length_3 > _maxAcc);
      if (_greaterThan) {
        float _length_4 = newAcc.length();
        float _maxAcc_1 = body.getMaxAcc();
        float _divide_3 = (_length_4 / _maxAcc_1);
        newAcc.scale(_divide_3);
      }
      return newAcc;
    }
  }
  
  @Extension
  @ImportedCapacityFeature(Logging.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_LOGGING;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(Logging.class, ($0$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || $0$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? ($0$CAPACITY_USE$IO_SARL_CORE_LOGGING = $0$getSkill(Logging.class)) : $0$CAPACITY_USE$IO_SARL_CORE_LOGGING)", imported = Logging.class)
  private Logging $CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = $getSkill(Logging.class);
    }
    return $castSkill(Logging.class, this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
  }
  
  @SyntheticMember
  public SeekingSkill() {
    super();
  }
  
  @SyntheticMember
  public SeekingSkill(final Agent agent) {
    super(agent);
  }
}

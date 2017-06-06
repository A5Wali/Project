package drone;

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
import javax.vecmath.Point3f;
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
  public Vector3f seekingFixedTarget(final List<DroneBody> listOfObstacle, final Sphere target) {
    final DroneBody body = listOfObstacle.get(0);
    Vector3f droneToTargetVector = new Vector3f();
    Point3f tarPos = target.getPosition();
    Point3f bodyPos = body.getPosition();
    droneToTargetVector.sub(tarPos, bodyPos);
    final float distanceDroneToTarget = droneToTargetVector.length();
    float _stopZone = body.getStopZone();
    boolean _lessEqualsThan = (distanceDroneToTarget <= _stopZone);
    if (_lessEqualsThan) {
      Vector3f stopVector = body.getCurrentSpeed();
      stopVector.negate();
      return stopVector;
    } else {
      Vector3f newAcc = droneToTargetVector;
      for (int i = 1; (i < ((Object[])Conversions.unwrapArray(listOfObstacle, Object.class)).length); i++) {
        newAcc.add(listOfObstacle.get(i).computeForces(body, target));
      }
      float _breakZone = body.getBreakZone();
      boolean _greaterThan = (distanceDroneToTarget > _breakZone);
      if (_greaterThan) {
        float _maxSpeed = body.getMaxSpeed();
        float _length = newAcc.length();
        float _divide = (_maxSpeed / _length);
        newAcc.scale(_divide);
      } else {
        float _maxSpeed_1 = body.getMaxSpeed();
        float _multiply = (_maxSpeed_1 * distanceDroneToTarget);
        float _breakZone_1 = body.getBreakZone();
        float _divide_1 = (_multiply / _breakZone_1);
        float _length_1 = newAcc.length();
        float _divide_2 = (_divide_1 / _length_1);
        newAcc.scale(_divide_2);
      }
      newAcc.sub(body.getCurrentSpeed());
      float _length_2 = newAcc.length();
      float _maxAcc = body.getMaxAcc();
      boolean _greaterThan_1 = (_length_2 > _maxAcc);
      if (_greaterThan_1) {
        float _length_3 = newAcc.length();
        float _maxAcc_1 = body.getMaxAcc();
        float _divide_3 = (_length_3 / _maxAcc_1);
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

package drone;

import drone.AccelerationMessage;
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
  public Vector3f seekingFixedTarget(final AccelerationMessage accelerationMessage, final Sphere target) {
    final DroneBody body = accelerationMessage.getDroneBody();
    Vector3f droneToTargetVector = new Vector3f();
    droneToTargetVector.sub(target.getPosition(), body.getPosition());
    final float distanceDroneToTarget = droneToTargetVector.length();
    float _stopZone = body.getStopZone();
    boolean _lessEqualsThan = (distanceDroneToTarget <= _stopZone);
    if (_lessEqualsThan) {
      Vector3f stopVector = body.getCurrentSpeed();
      stopVector.negate();
      return stopVector;
    } else {
      Vector3f newAcc = droneToTargetVector;
      float _max = Math.max(Math.max(body.getMaxAcc().x, body.getMaxAcc().y), body.getMaxAcc().z);
      float _length = newAcc.length();
      float _divide = (_max / _length);
      newAcc.scale(_divide);
      float _breakZone = body.getBreakZone();
      boolean _greaterThan = (distanceDroneToTarget > _breakZone);
      if (_greaterThan) {
        for (int i = 0; (i < ((Object[])Conversions.unwrapArray(accelerationMessage.getFrustum(), Object.class)).length); i++) {
          newAcc.add(accelerationMessage.getFrustum().get(i).computeForces(body, target));
        }
        Vector3f newAccABS = new Vector3f();
        Vector3f currentSpeedABS = new Vector3f();
        currentSpeedABS.absolute(body.getCurrentSpeed());
        float _length_1 = body.getCurrentSpeed().length();
        float _length_2 = body.getMaxSpeed().length();
        float _length_3 = body.getCurrentSpeed().length();
        float _minus = (_length_2 - _length_3);
        float _min = Math.min(_minus, body.getMaxAcc().length());
        float _plus = (_length_1 + _min);
        float _length_4 = newAcc.length();
        float _divide_1 = (_plus / _length_4);
        newAcc.scale(_divide_1);
        newAccABS.absolute(newAcc);
        if ((newAccABS.x > body.getMaxSpeed().x)) {
          newAcc.scale((body.getMaxSpeed().x / newAccABS.x));
          newAccABS.absolute(newAcc);
        }
        if ((newAccABS.y > body.getMaxSpeed().y)) {
          newAcc.scale((body.getMaxSpeed().y / newAccABS.y));
          newAccABS.absolute(newAcc);
        }
        if ((newAccABS.z > body.getMaxSpeed().z)) {
          newAcc.scale((body.getMaxSpeed().z / newAccABS.z));
          newAccABS.absolute(newAcc);
        }
        Vector3f subAcc = new Vector3f();
        subAcc.sub(newAcc, body.getCurrentSpeed());
        Vector3f subAccABS = new Vector3f();
        subAccABS.absolute(subAcc);
        if ((subAccABS.x > body.getMaxAcc().x)) {
          subAcc.scale((body.getMaxAcc().x / subAccABS.x));
          subAccABS.absolute(newAcc);
        }
        if ((subAccABS.y > body.getMaxAcc().y)) {
          subAcc.scale((body.getMaxAcc().y / subAccABS.y));
          subAccABS.absolute(newAcc);
        }
        if ((subAccABS.z > body.getMaxAcc().z)) {
          subAcc.scale((body.getMaxAcc().z / subAccABS.z));
        }
        Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
        Vector3f _maxAcc = body.getMaxAcc();
        String _plus_1 = ("maxAcc : " + _maxAcc);
        _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info(_plus_1);
        Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER_1 = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
        _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER_1.info(("Acc : " + subAcc));
        return subAcc;
      } else {
        float _length_5 = body.getMaxSpeed().length();
        float _breakZone_1 = body.getBreakZone();
        float _divide_2 = (distanceDroneToTarget / _breakZone_1);
        double _sqrt = Math.sqrt(_divide_2);
        float _multiply = (_length_5 * ((float) _sqrt));
        newAcc.scale(_multiply);
        newAcc.sub(newAcc, body.getCurrentSpeed());
        Vector3f newAccABS_1 = new Vector3f();
        newAccABS_1.absolute(newAcc);
        if ((newAccABS_1.x > body.getMaxAcc().x)) {
          newAcc.scale((body.getMaxAcc().x / newAccABS_1.x));
          newAccABS_1.absolute(newAcc);
        }
        if ((newAccABS_1.y > body.getMaxAcc().y)) {
          newAcc.scale((body.getMaxAcc().y / newAccABS_1.y));
          newAccABS_1.absolute(newAcc);
        }
        if ((newAccABS_1.z > body.getMaxAcc().z)) {
          newAcc.scale((body.getMaxAcc().z / newAccABS_1.z));
        }
        return newAcc;
      }
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

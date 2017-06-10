package drone;

import drone.AccelerationMessage;
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
import java.util.ArrayList;
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
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
    ArrayList<Cube> _frustum = accelerationMessage.getFrustum();
    String _plus = ("Frustum : " + _frustum);
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info(_plus);
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
      float _length = body.getMaxAcc().length();
      float _length_1 = newAcc.length();
      float _divide = (_length / _length_1);
      newAcc.scale(_divide);
      for (int i = 0; (i < ((Object[])Conversions.unwrapArray(accelerationMessage.getFrustum(), Object.class)).length); i++) {
        newAcc.add(accelerationMessage.getFrustum().get(i).computeForces(body, target));
      }
      float _breakZone = body.getBreakZone();
      boolean _greaterThan = (distanceDroneToTarget > _breakZone);
      if (_greaterThan) {
        Vector3f newAccABS = null;
        Vector3f currentSpeedABS = null;
        currentSpeedABS.absolute(body.getCurrentSpeed());
        float _length_2 = body.getCurrentSpeed().length();
        float _length_3 = body.getMaxSpeed().length();
        float _length_4 = body.getCurrentSpeed().length();
        float _minus = (_length_3 - _length_4);
        float _min = Math.min(_minus, body.getMaxAcc().length());
        float _plus_1 = (_length_2 + _min);
        float _length_5 = newAcc.length();
        float _divide_1 = (_plus_1 / _length_5);
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
        if (((newAccABS.x - currentSpeedABS.x) > body.getMaxAcc().x)) {
          newAcc.scale(((currentSpeedABS.x + body.getMaxAcc().x) / newAccABS.x));
          newAccABS.absolute(newAcc);
        }
        if (((newAccABS.y - currentSpeedABS.y) > body.getMaxAcc().y)) {
          newAcc.scale(((currentSpeedABS.y + body.getMaxAcc().y) / newAccABS.y));
        }
        Vector3f subAcc = null;
        subAcc.sub(newAcc, body.getCurrentSpeed());
        return subAcc;
      } else {
        float _length_6 = body.getMaxSpeed().length();
        float _multiply = (_length_6 * distanceDroneToTarget);
        float _breakZone_1 = body.getBreakZone();
        float _divide_2 = (_multiply / _breakZone_1);
        float _length_7 = body.getCurrentSpeed().length();
        float _minus_1 = (_divide_2 - _length_7);
        float _length_8 = newAcc.length();
        float _divide_3 = (_minus_1 / _length_8);
        newAcc.scale(_divide_3);
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

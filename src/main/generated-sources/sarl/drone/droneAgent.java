package drone;

import com.google.common.base.Objects;
import drone.CommunicationCapacity;
import drone.CommunicationSkill;
import drone.Cube;
import drone.DroneBody;
import drone.Message;
import drone.Moving;
import drone.ReceivedMessage;
import drone.SeekingSkill;
import drone.Sphere;
import drone.TypeMessage;
import io.sarl.core.AgentKilled;
import io.sarl.core.AgentSpawned;
import io.sarl.core.ContextJoined;
import io.sarl.core.ContextLeft;
import io.sarl.core.Destroy;
import io.sarl.core.Initialize;
import io.sarl.core.Lifecycle;
import io.sarl.core.Logging;
import io.sarl.core.MemberJoined;
import io.sarl.core.MemberLeft;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.Skill;
import io.sarl.lang.util.ClearableReference;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import javax.vecmath.Vector3f;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.5")
@SuppressWarnings("all")
public class DroneAgent extends Agent {
  private DroneBody body;
  
  private Sphere target;
  
  @SyntheticMember
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = $getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("The agent was started.");
    CommunicationSkill _communicationSkill = new CommunicationSkill();
    this.<CommunicationSkill>setSkill(_communicationSkill, CommunicationCapacity.class);
    SeekingSkill _seekingSkill = new SeekingSkill();
    this.<SeekingSkill>setSkill(_seekingSkill, Moving.class);
    final Message message = new Message(TypeMessage.SPAWN, "");
    CommunicationCapacity _$CAPACITY_USE$DRONE_COMMUNICATIONCAPACITY$CALLER = this.$castSkill(CommunicationCapacity.class, (this.$CAPACITY_USE$DRONE_COMMUNICATIONCAPACITY == null || this.$CAPACITY_USE$DRONE_COMMUNICATIONCAPACITY.get() == null) ? (this.$CAPACITY_USE$DRONE_COMMUNICATIONCAPACITY = $getSkill(CommunicationCapacity.class)) : this.$CAPACITY_USE$DRONE_COMMUNICATIONCAPACITY);
    _$CAPACITY_USE$DRONE_COMMUNICATIONCAPACITY$CALLER.sendMessage(message);
  }
  
  @SyntheticMember
  private void $behaviorUnit$ReceivedMessage$1(final ReceivedMessage occurrence) {
    Message o = occurrence.message;
    TypeMessage _type = o.getType();
    boolean _equals = Objects.equal(_type, TypeMessage.ACC);
    if (_equals) {
      Object _message = o.getMessage();
      final List<Cube> list = ((List<Cube>) _message);
      Moving _$CAPACITY_USE$DRONE_MOVING$CALLER = this.$castSkill(Moving.class, (this.$CAPACITY_USE$DRONE_MOVING == null || this.$CAPACITY_USE$DRONE_MOVING.get() == null) ? (this.$CAPACITY_USE$DRONE_MOVING = $getSkill(Moving.class)) : this.$CAPACITY_USE$DRONE_MOVING);
      final Vector3f v = _$CAPACITY_USE$DRONE_MOVING$CALLER.seekingFixedTarget(list, this.body, this.target);
      final Message message = new Message(TypeMessage.ACC, v);
      CommunicationCapacity _$CAPACITY_USE$DRONE_COMMUNICATIONCAPACITY$CALLER = this.$castSkill(CommunicationCapacity.class, (this.$CAPACITY_USE$DRONE_COMMUNICATIONCAPACITY == null || this.$CAPACITY_USE$DRONE_COMMUNICATIONCAPACITY.get() == null) ? (this.$CAPACITY_USE$DRONE_COMMUNICATIONCAPACITY = $getSkill(CommunicationCapacity.class)) : this.$CAPACITY_USE$DRONE_COMMUNICATIONCAPACITY);
      _$CAPACITY_USE$DRONE_COMMUNICATIONCAPACITY$CALLER.sendMessage(message);
    } else {
      TypeMessage _type_1 = o.getType();
      boolean _equals_1 = Objects.equal(_type_1, TypeMessage.SPAWN);
      if (_equals_1) {
        Object _message_1 = o.getMessage();
        this.body = ((DroneBody) _message_1);
      } else {
        TypeMessage _type_2 = o.getType();
        boolean _equals_2 = Objects.equal(_type_2, TypeMessage.DELETE);
        if (_equals_2) {
          Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$castSkill(Lifecycle.class, (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = $getSkill(Lifecycle.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
          _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.killMe();
        } else {
          TypeMessage _type_3 = o.getType();
          boolean _equals_3 = Objects.equal(_type_3, TypeMessage.TARGET);
          if (_equals_3) {
            Object _message_2 = o.getMessage();
            this.target = ((Sphere) _message_2);
          }
        }
      }
    }
  }
  
  @SyntheticMember
  private void $behaviorUnit$Destroy$2(final Destroy occurrence) {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = $getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("The agent was stopped.");
  }
  
  @SyntheticMember
  private void $behaviorUnit$AgentSpawned$3(final AgentSpawned occurrence) {
  }
  
  @SyntheticMember
  private void $behaviorUnit$AgentKilled$4(final AgentKilled occurrence) {
  }
  
  @SyntheticMember
  private void $behaviorUnit$ContextJoined$5(final ContextJoined occurrence) {
  }
  
  @SyntheticMember
  private void $behaviorUnit$ContextLeft$6(final ContextLeft occurrence) {
  }
  
  @SyntheticMember
  private void $behaviorUnit$MemberJoined$7(final MemberJoined occurrence) {
  }
  
  @SyntheticMember
  private void $behaviorUnit$MemberLeft$8(final MemberLeft occurrence) {
  }
  
  @Extension
  @ImportedCapacityFeature(Logging.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_LOGGING;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = $getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING)", imported = Logging.class)
  private Logging $CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = $getSkill(Logging.class);
    }
    return $castSkill(Logging.class, this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
  }
  
  @Extension
  @ImportedCapacityFeature(Lifecycle.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_LIFECYCLE;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(Lifecycle.class, (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = $getSkill(Lifecycle.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE)", imported = Lifecycle.class)
  private Lifecycle $CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = $getSkill(Lifecycle.class);
    }
    return $castSkill(Lifecycle.class, this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
  }
  
  @Extension
  @ImportedCapacityFeature(CommunicationCapacity.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$DRONE_COMMUNICATIONCAPACITY;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(CommunicationCapacity.class, (this.$CAPACITY_USE$DRONE_COMMUNICATIONCAPACITY == null || this.$CAPACITY_USE$DRONE_COMMUNICATIONCAPACITY.get() == null) ? (this.$CAPACITY_USE$DRONE_COMMUNICATIONCAPACITY = $getSkill(CommunicationCapacity.class)) : this.$CAPACITY_USE$DRONE_COMMUNICATIONCAPACITY)", imported = CommunicationCapacity.class)
  private CommunicationCapacity $CAPACITY_USE$DRONE_COMMUNICATIONCAPACITY$CALLER() {
    if (this.$CAPACITY_USE$DRONE_COMMUNICATIONCAPACITY == null || this.$CAPACITY_USE$DRONE_COMMUNICATIONCAPACITY.get() == null) {
      this.$CAPACITY_USE$DRONE_COMMUNICATIONCAPACITY = $getSkill(CommunicationCapacity.class);
    }
    return $castSkill(CommunicationCapacity.class, this.$CAPACITY_USE$DRONE_COMMUNICATIONCAPACITY);
  }
  
  @Extension
  @ImportedCapacityFeature(Moving.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$DRONE_MOVING;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(Moving.class, (this.$CAPACITY_USE$DRONE_MOVING == null || this.$CAPACITY_USE$DRONE_MOVING.get() == null) ? (this.$CAPACITY_USE$DRONE_MOVING = $getSkill(Moving.class)) : this.$CAPACITY_USE$DRONE_MOVING)", imported = Moving.class)
  private Moving $CAPACITY_USE$DRONE_MOVING$CALLER() {
    if (this.$CAPACITY_USE$DRONE_MOVING == null || this.$CAPACITY_USE$DRONE_MOVING.get() == null) {
      this.$CAPACITY_USE$DRONE_MOVING = $getSkill(Moving.class);
    }
    return $castSkill(Moving.class, this.$CAPACITY_USE$DRONE_MOVING);
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Initialize(final Initialize occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Initialize$0(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$ContextLeft(final ContextLeft occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$ContextLeft$6(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$ContextJoined(final ContextJoined occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$ContextJoined$5(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$MemberLeft(final MemberLeft occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$MemberLeft$8(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$AgentSpawned(final AgentSpawned occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$AgentSpawned$3(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$ReceivedMessage(final ReceivedMessage occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$ReceivedMessage$1(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Destroy(final Destroy occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Destroy$2(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$AgentKilled(final AgentKilled occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$AgentKilled$4(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$MemberJoined(final MemberJoined occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$MemberJoined$7(occurrence));
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
    DroneAgent other = (DroneAgent) obj;
    if (this.body == null) {
      if (other.body != null)
        return false;
    } else if (!this.body.equals(other.body))
      return false;
    if (this.target == null) {
      if (other.target != null)
        return false;
    } else if (!this.target.equals(other.target))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.body== null) ? 0 : this.body.hashCode());
    result = prime * result + ((this.target== null) ? 0 : this.target.hashCode());
    return result;
  }
  
  @SyntheticMember
  public DroneAgent(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public DroneAgent(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
}

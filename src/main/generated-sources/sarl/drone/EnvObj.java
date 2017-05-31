package drone;

import drone.Cuboid;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import javax.vecmath.Point3f;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Alexandre
 */
@SarlSpecification("0.5")
@SuppressWarnings("all")
public class EnvObj {
  private Point3f position;
  
  public static Cuboid target;
  
  public EnvObj() {
    Point3f _point3f = new Point3f();
    this.position = _point3f;
  }
  
  public EnvObj(final Point3f pos) {
    this.position = pos;
  }
  
  public Point3f setPosition(final Point3f pos) {
    return this.position = pos;
  }
  
  @Pure
  public Point3f getPosition() {
    return this.position;
  }
  
  public Cuboid setTarget(final Point3f pos, final float w, final float h, final float l) {
    Cuboid _cuboid = new Cuboid(pos, w, h, l);
    return EnvObj.target = _cuboid;
  }
  
  @Pure
  public Cuboid getTarget() {
    return EnvObj.target;
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
    EnvObj other = (EnvObj) obj;
    if (this.position == null) {
      if (other.position != null)
        return false;
    } else if (!this.position.equals(other.position))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.position== null) ? 0 : this.position.hashCode());
    return result;
  }
}

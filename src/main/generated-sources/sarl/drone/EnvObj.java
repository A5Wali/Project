package drone;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import javax.vecmath.Point3f;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Alexandre
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class EnvObj {
  private Point3f position;
  
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
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    return result;
  }
}

package drone;

import drone.EnvObj;
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
}

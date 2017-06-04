package drone;

import drone.EnvObj;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.io.Serializable;
import javax.vecmath.Point3f;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Alexandre
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class Sphere extends EnvObj implements Serializable {
  private float radius;
  
  public Sphere() {
    super();
    this.radius = 0.0f;
  }
  
  public Sphere(final Point3f pos, final float r) {
    super(pos);
    this.radius = r;
  }
  
  /**
   * def setRadius(r : float){
   * this.radius = r
   * }
   */
  @Pure
  public float getRadius() {
    return this.radius;
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
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Float.floatToIntBits(this.radius);
    return result;
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 583118532L;
}

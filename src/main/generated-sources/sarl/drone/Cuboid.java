package drone;

import drone.EnvObj;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.io.Serializable;
import javax.vecmath.Point3f;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Alexandre
 */
@SarlSpecification("0.5")
@SuppressWarnings("all")
public class Cuboid extends EnvObj implements Serializable {
  private float width;
  
  private float height;
  
  private float length;
  
  public Cuboid() {
    super();
    this.width = 0.0f;
    this.height = 0.0f;
    this.length = 0.0f;
  }
  
  public Cuboid(final Point3f pos, final float w, final float h, final float l) {
    super(pos);
    this.width = w;
    this.height = h;
    this.length = l;
  }
  
  /**
   * def setDimension(w : float, h : float, l : float) {
   * this.width = w
   * this.height = h
   * this.length = l
   * }
   */
  @Pure
  public float getwidth() {
    return this.width;
  }
  
  @Pure
  public float getHeight() {
    return this.height;
  }
  
  @Pure
  public float getLength() {
    return this.length;
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
    Cuboid other = (Cuboid) obj;
    if (Float.floatToIntBits(other.width) != Float.floatToIntBits(this.width))
      return false;
    if (Float.floatToIntBits(other.height) != Float.floatToIntBits(this.height))
      return false;
    if (Float.floatToIntBits(other.length) != Float.floatToIntBits(this.length))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + Float.floatToIntBits(this.width);
    result = prime * result + Float.floatToIntBits(this.height);
    result = prime * result + Float.floatToIntBits(this.length);
    return result;
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 4598174888L;
}

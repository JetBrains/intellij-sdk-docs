package MyPackage;

/**
 * Created by IntelliJ IDEA.
 * User: Alexey.Chursin
 * Date: Aug 13, 2010
 * Time: 3:49:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyCounter {
  private int Count = 0;
  // Sets the maximum allowed number of opened projects.
  public final int MaxCount = 3;

  public MyCounter() {


  }

  public int IncreaseCounter() {
    Count++;
    return (Count > MaxCount) ? -1 : Count;
  }

  public int DecreaseCounter() {
    Count--;
    return (Count > MaxCount) ? -1 : Count;
  }

}

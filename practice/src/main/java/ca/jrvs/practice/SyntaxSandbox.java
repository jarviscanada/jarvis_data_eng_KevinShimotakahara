package ca.jrvs.practice;

public class SyntaxSandbox {
  int bleh = 2;
  int test = 4;
  public int getBleh() {
    return bleh;
  }

  public void setBleh(int bleh) {
    this.bleh = bleh;
  }

  public static void main(String[] args) {
      double bleh = Double.parseDouble("tisd");
      bleh = bleh + 0.31;

      System.out.println(bleh);
  }
}

package ca.jrvs.apps.practice;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExcImp implements RegexExc {
  public RegexExcImp(){

  }

  public boolean matchJpeg(String filename) {
    Pattern pattern = Pattern.compile(".+\\.(jpg|jpeg)$",Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(filename);
    return matcher.find();
  }
  public boolean matchIp(String ip){
    Pattern pattern = Pattern.compile("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}");
    Matcher matcher = pattern.matcher(ip);
    return matcher.find();
  }
  public boolean isEmptyLine(String line){
    Pattern pattern = Pattern.compile("^\\s*$");
    Matcher matcher = pattern.matcher(line);
    return matcher.find();
  }

  public static void main(String[] args) {
    RegexExcImp regexThing = new RegexExcImp();
    if (regexThing.matchJpeg("yo.JPeEg")) {
      System.out.println("This is a jpeg");
    }else{
      System.out.println("This is not a jpeg");
    }
    if (regexThing.matchIp("090.f30.0.000")) {
      System.out.println("This is a valid IP");
    }else{
      System.out.println("This is not a valid IP");
    }
    if (regexThing.isEmptyLine(" f ")) {
      System.out.println("This line is empty");
    }else{
      System.out.println("This line is not empty");
    }
  }

}

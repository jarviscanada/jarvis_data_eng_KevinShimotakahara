package ca.jrvs.apps.grep;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.nio.file.Files;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.BasicConfigurator;

public class JavaGrepLambdaImp extends JavaGrepImp{

  public static void main(String[] args) throws FileNotFoundException {
    if (args.length != 3){
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }
    //creating JavaGrepLambdaImp instead of JavaGrepImp
    //JavaGrepLambdaImp inherits all methods except two override methods in JavaGrepImp
    JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
    javaGrepLambdaImp.setRegex(args[0]);
    javaGrepLambdaImp.setRootPath(args[1]);
    javaGrepLambdaImp.setOutFile(args[2]);

    try {
      //calling parent method,
      //but it will call override method (in this class)
      javaGrepLambdaImp.process();
    } catch (Exception ex){
      ex.printStackTrace();
    }
  }


  /**
   * Implement using lambda and stream APIs
   */
  @Override
  public List<String> readLines(File inputFile) throws IllegalArgumentException, FileNotFoundException {
    List<String> fileLines = new ArrayList<String>();
    try{
      Files.lines(Path.of(inputFile.getPath())).forEach(fileLines::add);
    }catch (IOException ex){
      ex.printStackTrace();
    }
    return fileLines;
  }

  /**
   * Implement using lambda and stream APIs
   */
  @Override
  public List<File> listFiles(String rootDir) {
    File dir = new File(rootDir);
    File[] files = dir.listFiles();
    List<File> filesFound = new ArrayList<File>();
    if (files != null && files.length > 0){
      Arrays.stream(files).forEach(file -> {
        if (file.isDirectory()){
          //recursively call listFiles
          filesFound.addAll(listFiles(file.getAbsolutePath()));
        }else{
          filesFound.add(file);
        }
      });
    }
    return filesFound;
  }
}

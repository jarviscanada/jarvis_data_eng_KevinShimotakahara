package ca.jrvs.apps.grep;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.BasicConfigurator;

public class JavaGrepImp implements JavaGrep {

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  private String regex;
  private String rootPath;
  private String outFile;
  private PrintWriter out;

  public JavaGrepImp(String regex, String rootPath, String outFile){
    this.regex = regex;
    this.rootPath = rootPath;
    this.outFile = outFile;
  }

  /**
   * Top level search workflow
   * @throws IOException
   */
  public void process() throws IOException {
    try {
      FileWriter fw = new FileWriter(outFile, true);
      BufferedWriter bw = new BufferedWriter(fw);
      this.out = new PrintWriter(bw);
    } catch (IOException e) {
      logger.error("Failed to create FileWriter; check if output file path is valid.",e);
    }

    for (File file : listFiles(rootPath)){
      readLines(file).filter(line -> containsPattern(line)).forEach(matchedLine -> {
        try {
          writeToFile(matchedLine);
        } catch (IOException e) {
          logger.error("Failed to write to file.",e);
        }
      });
    }
    this.out.close();
  }

  /**
   * Traverse a given directory and return all files
   * @param rootDir input directory
   * @return files under the rootDir
   */
  public List<File> listFiles(String rootDir){
    File dir = new File(rootDir);
    File[] files = dir.listFiles();
    List<File> filesFound = new ArrayList<File>();
    if (files != null && files.length > 0){
      for (File file : files){
        // Check if the file is a directory
        if (file.isDirectory()){
          //recursively call listFiles this is a sanity check
          filesFound.addAll(listFiles(file.getAbsolutePath()));
        }else{
          filesFound.add(file);
        }
      }
    }
    return filesFound;
  }

  /**
   * Read a file and return all the lines
   *
   * Explain FileReader, BufferedReader, and character encoding
   *
   * @param inputFile file to be read
   * @return lines
   * @throws IllegalArgumentException if a given inputFile is not a file
   * @throws FileNotFoundException
   */
  public Stream<String> readLines(File inputFile)
      throws IllegalArgumentException, FileNotFoundException {
    Stream<String> fileLines = null;
    try{
      fileLines = Files.lines(inputFile.toPath());//.forEach(fileLines::add);
    }catch (IOException ex){
      logger.error("Failed to create fileLines stream; might be an issue with input file path.",ex);
    }
    return fileLines;
  }

  /**
   * check if a line contains the regex patter (passed by user)
   * @param line input string
   * @return true if there is a match
   */
  public boolean containsPattern(String line) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(line);
    return matcher.find();
  }

  /**
   * Write lines to a file
   *
   * Explore: FileOutputStream, OutputStreamWriter, and BufferedWriter
   *
   * @param line matched line
   * @throws IOException if write failed
   */
  public void writeToFile(String line) throws IOException {
    this.out.println(line);
  }

  public String getRootPath(){
    return this.rootPath;
  }

  public void setRootPath(String rootPath){
    this.rootPath = rootPath;
  }

  public String getRegex(){
    return this.regex;
  }

  public void setRegex(String regex){
    this.regex = regex;
  }

  public String getOutFile(){
    return this.outFile;
  }

  public void setOutFile(String outFile){
    this.outFile = outFile;
  }

  public static void main(String[] args) throws FileNotFoundException {
    if (args.length != 3){
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    //Use default logger config
    BasicConfigurator.configure();

    JavaGrepImp javaGrepImp = new JavaGrepImp(args[0],args[1],args[2]);

    try {
      javaGrepImp.process();
    } catch (Exception ex) {
      javaGrepImp.logger.error(ex.getMessage(), ex);
    }

  }
}

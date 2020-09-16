# Introduction
This application recreates partial functionality of the "grep" 
command commonly used in the Linux command line. More specifically,
given an input regular expression, directory, and output path/filename,
it will search through all files found in the directory and all its
sub directories for instances of the regular expression, and print
the lines of text containing these instances into the indicated
file.

This application uses functional programming techniques for efficient
iteration through file systems, and the contents of files themselves.

This project served as a learning experience. Over the course of its
development, I learned much about the IntelliJ IDE, Java Regular
Expressions, leveraging io libraries for file manipulation, replacing
conventional looping techniques with internally iterable stream objects
into which lambda functions can be passed to their intermediate and
terminal methods, and the Maven project management framework.

# Usage
Input Parameters/Arguments:
1. **regex**: a Regular Expression text string for describing a search pattern
2. **rootPath**: root directory path
3. **outFile**: output file name including path

If you are familiar with (e)grep, these parameters would be used
as follows:

`egrep -r {regex} {rootPath} > {outFile}`

Just as the above command would
1. **Inspect** all files inside the
 indicated directory `rootPath` (including sub-directories)
2. **Print** all lines of each file inspected that match the `regex` 
 pattern provided into a text file
3. **Save** the file in the directory and under the name provided by
`outFile1

This application operates the same way. To get started with an
example, pull the project code, and navigate to the project's
root directory called "grep" in your terminal. Then, run
the commands below:

```shell script
#make an exampleDirectory in your home directory
mkdir exampleDirectory
#make a file
touch exampleDirectory/example.txt

#add some lines, some with the word "hot dog" in it:
cat << EOF > exampleDirectory/example.txt
Once upon a time there was a cat.
This cat had a hankering for cheeseburgers.
But all the cat could find was a hot dog stand.
It burned the hot dog stand to the ground and took a nap.
And this is how McDonalds was created.
EOF

#make a nested directory, put another text file in it
mkdir exampleDirectory/nestedDirectory
touch exampleDirectory/nestedDirectory/nestedExample.txt

#add some text
cat << EOF > exampleDirectory/nestedDirectory/nestedExample.txt
Once upon a time there was a hot dog.
This hot dog swore to exact vengeance on the cat that destroyed its home.
It snuck up on the sleeping cat and tried to hit it.
But the cat woke up and batted it away.
The hot dog landed in the street and got run over.
EOF

#clean and build project
mvn clean package
#Run jar with inputs; code should find all lines with "hot dog" in it
java -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepImp .*hot.*dog.* ./exampleDirectory ./out/grep33.txt
#verify 
cat exampleDirectory/outputExample.txt
```

# Pseudocode
Below is the `process` method pseudocode that does
the primary operations of the application.
```java
public void process(){
  for (each file in target directory){
    readLines(file) //creates Files.lines() Stream object
      .filter(line -> containsPattern(line)) //checks if line matches regex
      .forEach(matchedLine -> writeToFile(matchedLine); //appends matches to output file
  }
}
```
1: do the stuff. (LaTeX)

# Performance Issue
A potential performance issue identified during the
design process was the risk of overloading the JVM's
heap memory should the files be excessively large, or should the
max heap size be set excessively small.

This issue was fixed by implementing streams to eliminate the need
for saving an entire file's worth of text in a list object. Instead,
a Files.lines() stream "lazily" loads one line at a time from the 
file source into heap memory as they are processed by our algorithm.
Moreover, we directly write each line we find that matches the
regex pattern to the output file without the use of an intermediary
list object.

# Improvement
A few things have been identified that can add value to this project:

1. Add an option to print the matches found automatically
2. Handle exceptions more rigorously (craft descriptive error
messages that help the user understand the problem)
3. Partner with Neuralink so user can execute the program
telepathically
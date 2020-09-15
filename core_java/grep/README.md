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

This application operates the same way.
   Show a simple example
```shell script
#make an exampleDirectory in your home directory
mkdir ~/exampleDirectory
#make a file
touch ~/exampleDirectory/example.txt

#add some lines, one with the word "hot dog" in it:
cat <<- EOF > example.txt
Once upon a time there was a cat.
This cat had a hankering for cheeseburgers.
But all the cat could find was a hot dog stand.
It burned the hot dog stand to the ground and took a nap.
And this is how McDonalds was created.
EOF

#make a nested directory, put another text file in it
mkdir ~/exampleDirectory/nestedDirectory
touch ~/exampleDirectory/nestedDirectory/nestedExample.txt

#add some text
cat <<- EOF > nestedExample.txt
Once upon a time there was a hot dog.
This hot dog swore to exact vengeance on the cat that destroyed its home.
It snuck up on the sleeping cat and tried to hit it.
But the cat woke up and batted it away.
The hot dog landed in the street and got run over.
EOF

#Download jar file
wget -O grep-demo.jar https://github.com/jarviscanada/jarvis_data_eng_demo/raw/feature/grep_demo_jar/core_java/grep/target/grep-1.0-SNAPSHOT.jar

#Run jar with inputs
java -jar grep-demo.jar "hot dog" "//home//centos//exampleDirectory" "//home//centos//exampleDirectory//outputExample.txt"

#verify 
cat ~/exampleDirectory/outputExample.txt
```

# Pseudocode
write `process` method pseudocode.

1: do the stuff.

# Performance Issue
(30-60 words)
discuss the memory issue

...Memory Issue?

# Improvement
List three things you can improve in this project.

1. Don't use this project and use grep instead
for i in `find . -name "*.java"`
do
	if [ ! -f ../Test/$i ];
	then
    dir=`dirname $i`
 		mkdir -p ../Test/$dir
    file="../Test/`echo $i | sed -e 's/\.java//g'`Test"
 		touch "$file".java
    file="$file".java
    echo "@SuppressWarnings(\"javadoc\")" >> $file
    echo "public class `basename $file`" >> $file
    echo "\{" >> $file
    
    echo "\t@Before" >> $file
    echo "public void setUp ()" >> $file
    echo "\{" >> $file
    echo "\}" >> $file
    
    echo "\t@Test" >> $file
    echo "public void Test ()" >> $file
    echo "\{" >> $file
    echo "\}" >> $file

    echo "\}" >> $file

	fi
done

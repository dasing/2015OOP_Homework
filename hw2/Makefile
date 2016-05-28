JC = javac
JFLAGS = -g -sourcepath src/
JVM = java -classpath src
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
CLASSES = \
	src/POOCasino.java \
	src/Player.java \
	src/Card.java  \
	src/Computer.java \
	src/Shuffler.java
MAIN = POOCasino
default: classes
classes: $(CLASSES:.java=.class)
run: classes
	 $(JVM) $(MAIN)
clean:
	$(RM)*.class

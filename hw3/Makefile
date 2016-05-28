JC = javac
JFLAGS = -g -sourcepath src/
JVM = java -classpath src
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
CLASSES = \
	src/POOCasino.java \
	src/User.java \
	src/Card.java  \
	src/Computer.java \
	src/Shuffler.java \
	src/OldMaid.java \
	src/VariantOne.java \
	src/VariantTwo.java \
	src/Player.java
MAIN = POOCasino
default: classes
classes: $(CLASSES:.java=.class)
run: classes
	 $(JVM) $(MAIN)
clean:
	$(RM)*.class

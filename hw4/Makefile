JC = javac
JFLAGS = -g -classpath lib -sourcepath src/ 
JVM = java -classpath src:lib 
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
CLASSES = \
	src/POOCasino.java \
	src/Dealer.java \
	src/Shuffler.java \
	src/PlayerB01902135.java 
MAIN = POOCasino 4 5 PlayerB01902135 PlayerB01902058 PlayerB01902135 PlayerB01902058
default: classes
classes: $(CLASSES:.java=.class)
run: classes
	 $(JVM) $(MAIN) 
clean:
	$(RM)*.class

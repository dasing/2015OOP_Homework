# define compiler and compiler flag variables
GUISOURCEPATH = src/gui/
GENSOURCEPATH = src/generic/
DOCSOURCEPATH = src/doc/

JFLAGS = -g -classpath lib -sourcepath $(GENSOURCEPATH):$(GUISOURCEPATH):$(DOCSOURCEPATH)
JC = javac

# Clear any default targets for building .class files from .java files;
# we will provide our own target entry to do this in this makefile.
# make has a set of default targets for different suffixes (like .c.o) 
# Currently, clearing the default for .java.class is not necessary since 
# make does not have a definition for this target, but later versions of 
# make may, so it doesn't hurt to make sure that we clear any default 
# definitions for these
.SUFFIXES: .java .class

# Here is our target entry for creating .class files from .java files 
# This is a target entry that uses the suffix rule syntax:
#	DSTS:
#		rule
#  'TS' is the suffix of the target file, 
#  'DS' is the suffix of the dependency file, and 
#  'rule' is the rule for building a target	
#  '$*' is a built-in macro that gets the basename of the current target 
# Remember that there must be a < tab > before the command line ('rule') 
.java.class: 
	$(JC) $(JFLAGS) $*.java

# CLASSES is a macro consisting of 4 words (one for each java source file)
CLASSES = \
        $(GENSOURCEPATH)Command.java \
        $(GENSOURCEPATH)PlayerAction.java \
        $(GENSOURCEPATH)MineBoard.java \
		$(GUISOURCEPATH)Fieldname.java \
        $(GUISOURCEPATH)BasePanel.java \
        $(GUISOURCEPATH)CardPanel.java \
        $(GUISOURCEPATH)RolePanel.java \
        $(GUISOURCEPATH)ActionsPanel.java \
        $(GUISOURCEPATH)HandFaceUpPanel.java \
        $(GUISOURCEPATH)HandFaceDownPanel.java \
        $(GUISOURCEPATH)PlayerPanel.java \
        $(GUISOURCEPATH)ComputerPanel.java \
        $(GUISOURCEPATH)RoleDeckPanel.java \
        $(GUISOURCEPATH)RewardDeckPanel.java \
        $(GUISOURCEPATH)DrawDeckPanel.java \
        $(GUISOURCEPATH)DiscardFaceUpDeckPanel.java \
        $(GUISOURCEPATH)DiscardFaceDownDeckPanel.java \
        $(GUISOURCEPATH)MineBoardPanel.java \
        $(GUISOURCEPATH)ButtonPanel.java \
        $(GUISOURCEPATH)BackgroundPanel.java \
        $(GUISOURCEPATH)GameFrame.java \
        $(GUISOURCEPATH)AnimationPanel.java \
        $(GUISOURCEPATH)GUIController.java \
        $(DOCSOURCEPATH)Card.java \
        $(DOCSOURCEPATH)Decide.java \
        $(DOCSOURCEPATH)Decision.java \
        $(DOCSOURCEPATH)Deck.java \
        $(DOCSOURCEPATH)GUIUseCardType.java \
        $(DOCSOURCEPATH)Shuffler.java \
        $(DOCSOURCEPATH)Status.java \
        $(DOCSOURCEPATH)Player.java \
        $(DOCSOURCEPATH)ComPlayer.java \
        $(DOCSOURCEPATH)core.java
        

# the default make target entry
default: classes

# This target entry uses Suffix Replacement within a macro: 
# $(name:string1=string2)
# In the words in the macro named 'name' replace 'string1' with 'string2'
# Below we are replacing the suffix .java of all words in the macro CLASSES 
# with the .class suffix
classes: $(CLASSES:.java=.class)

# RM is a predefined macro in make (RM = rm -f)
clean:
	rm -f $(GUISOURCEPATH)*.class $(GENSOURCEPATH)*.class $(DOCSOURCEPATH)*.class

run:
	java -cp $(GUISOURCEPATH):$(GENSOURCEPATH):$(DOCSOURCEPATH) core
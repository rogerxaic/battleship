JAVAC=javac
JFLAGS = -d build/ -s src/

all:
	$(JAVAC) $(JFLAGS) src/*/*.ja* -Xlint

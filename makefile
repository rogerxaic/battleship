JAVAC=javac
JAVA=java
JFLAGS = -d build/ -s src/
RFLAGS = -cp build
RUN=BatailleNavale

all:
	$(JAVAC) $(JFLAGS) src/*/*.ja*

run: install
	./$(RUN)

errors:
	mkdir build/
	$(JAVAC) $(JFLAGS) src/*/*.ja* -Xlint

install:
	echo "#!/bin/bash" > $(RUN);
	echo "" >> $(RUN);
	echo "export COLUMNS=\$$(tput cols)" >> $(RUN);
	echo "export LINES=\$$(tput lines)" >> $(RUN);
	echo "">>$(RUN);
	echo "java -cp build battleship.Battleship lc165x38">>$(RUN);
	chmod +x $(RUN)

update:
	git pull origin master

push:
	git add .
	git status
	git commit -m "$$$$"
	git push origin master

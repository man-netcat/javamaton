cleanrun: remake run

all: main

clean:
	rm -r bin/*


# Get all .java files
JAVA_FILES = $(shell find src -iname '*.java')
# Turn them into .class files
JAVA_FILES_CLASS = $(JAVA_FILES:src/%.java=bin/%.class)

# Get the file with public static void main
MAIN_FILE = $(shell grep "public static void main" -r src | cut -d':' -f1)
MAIN_FILENAME = $(shell basename "${MAIN_FILE}" | sed 's/.java$$//')

# Get the package from the main file
PACKAGE = $(shell grep "package" ${MAIN_FILE} | cut -d' ' -f2 | cut -d';' -f1)


# Specify how to compile each .java file into a .class file
$(JAVA_FILES_CLASS): bin/%.class: src/%.java
	javac -sourcepath bin/ -cp bin/ -d bin/ -s bin/ -h bin/ $<

# Build total package
main: $(JAVA_FILES_CLASS)


debug:
	@echo ${JAVA_FILES}
	@echo ${JAVA_FILES_CLASS}
	@echo ${MAIN_FILE}
	@echo ${MAIN_FILENAME}
	@echo ${PACKAGE}


run:
	java -cp bin/ "${PACKAGE}.${MAIN_FILENAME}"

remake: clean all

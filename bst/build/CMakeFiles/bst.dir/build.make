# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.22

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Disable VCS-based implicit rules.
% : %,v

# Disable VCS-based implicit rules.
% : RCS/%

# Disable VCS-based implicit rules.
% : RCS/%,v

# Disable VCS-based implicit rules.
% : SCCS/s.%

# Disable VCS-based implicit rules.
% : s.%

.SUFFIXES: .hpux_make_needs_suffix_list

# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

#Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/ztzhu/courses/Princeton-Algorithm/bst

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/ztzhu/courses/Princeton-Algorithm/bst/build

# Include any dependencies generated for this target.
include CMakeFiles/bst.dir/depend.make
# Include any dependencies generated by the compiler for this target.
include CMakeFiles/bst.dir/compiler_depend.make

# Include the progress variables for this target.
include CMakeFiles/bst.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/bst.dir/flags.make

CMakeFiles/bst.dir/bst.cpp.o: CMakeFiles/bst.dir/flags.make
CMakeFiles/bst.dir/bst.cpp.o: ../bst.cpp
CMakeFiles/bst.dir/bst.cpp.o: CMakeFiles/bst.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/ztzhu/courses/Princeton-Algorithm/bst/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/bst.dir/bst.cpp.o"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT CMakeFiles/bst.dir/bst.cpp.o -MF CMakeFiles/bst.dir/bst.cpp.o.d -o CMakeFiles/bst.dir/bst.cpp.o -c /home/ztzhu/courses/Princeton-Algorithm/bst/bst.cpp

CMakeFiles/bst.dir/bst.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/bst.dir/bst.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/ztzhu/courses/Princeton-Algorithm/bst/bst.cpp > CMakeFiles/bst.dir/bst.cpp.i

CMakeFiles/bst.dir/bst.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/bst.dir/bst.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/ztzhu/courses/Princeton-Algorithm/bst/bst.cpp -o CMakeFiles/bst.dir/bst.cpp.s

CMakeFiles/bst.dir/main.cpp.o: CMakeFiles/bst.dir/flags.make
CMakeFiles/bst.dir/main.cpp.o: ../main.cpp
CMakeFiles/bst.dir/main.cpp.o: CMakeFiles/bst.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/ztzhu/courses/Princeton-Algorithm/bst/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/bst.dir/main.cpp.o"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT CMakeFiles/bst.dir/main.cpp.o -MF CMakeFiles/bst.dir/main.cpp.o.d -o CMakeFiles/bst.dir/main.cpp.o -c /home/ztzhu/courses/Princeton-Algorithm/bst/main.cpp

CMakeFiles/bst.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/bst.dir/main.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/ztzhu/courses/Princeton-Algorithm/bst/main.cpp > CMakeFiles/bst.dir/main.cpp.i

CMakeFiles/bst.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/bst.dir/main.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/ztzhu/courses/Princeton-Algorithm/bst/main.cpp -o CMakeFiles/bst.dir/main.cpp.s

# Object files for target bst
bst_OBJECTS = \
"CMakeFiles/bst.dir/bst.cpp.o" \
"CMakeFiles/bst.dir/main.cpp.o"

# External object files for target bst
bst_EXTERNAL_OBJECTS =

bst: CMakeFiles/bst.dir/bst.cpp.o
bst: CMakeFiles/bst.dir/main.cpp.o
bst: CMakeFiles/bst.dir/build.make
bst: CMakeFiles/bst.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/ztzhu/courses/Princeton-Algorithm/bst/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Linking CXX executable bst"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/bst.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/bst.dir/build: bst
.PHONY : CMakeFiles/bst.dir/build

CMakeFiles/bst.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/bst.dir/cmake_clean.cmake
.PHONY : CMakeFiles/bst.dir/clean

CMakeFiles/bst.dir/depend:
	cd /home/ztzhu/courses/Princeton-Algorithm/bst/build && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/ztzhu/courses/Princeton-Algorithm/bst /home/ztzhu/courses/Princeton-Algorithm/bst /home/ztzhu/courses/Princeton-Algorithm/bst/build /home/ztzhu/courses/Princeton-Algorithm/bst/build /home/ztzhu/courses/Princeton-Algorithm/bst/build/CMakeFiles/bst.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/bst.dir/depend


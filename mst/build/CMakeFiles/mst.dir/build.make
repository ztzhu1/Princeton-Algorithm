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
CMAKE_SOURCE_DIR = /home/ztzhu/courses/Princeton-Algorithm/mst

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/ztzhu/courses/Princeton-Algorithm/mst/build

# Include any dependencies generated for this target.
include CMakeFiles/mst.dir/depend.make
# Include any dependencies generated by the compiler for this target.
include CMakeFiles/mst.dir/compiler_depend.make

# Include the progress variables for this target.
include CMakeFiles/mst.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/mst.dir/flags.make

CMakeFiles/mst.dir/UF.cpp.o: CMakeFiles/mst.dir/flags.make
CMakeFiles/mst.dir/UF.cpp.o: ../UF.cpp
CMakeFiles/mst.dir/UF.cpp.o: CMakeFiles/mst.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/ztzhu/courses/Princeton-Algorithm/mst/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/mst.dir/UF.cpp.o"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT CMakeFiles/mst.dir/UF.cpp.o -MF CMakeFiles/mst.dir/UF.cpp.o.d -o CMakeFiles/mst.dir/UF.cpp.o -c /home/ztzhu/courses/Princeton-Algorithm/mst/UF.cpp

CMakeFiles/mst.dir/UF.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/mst.dir/UF.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/ztzhu/courses/Princeton-Algorithm/mst/UF.cpp > CMakeFiles/mst.dir/UF.cpp.i

CMakeFiles/mst.dir/UF.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/mst.dir/UF.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/ztzhu/courses/Princeton-Algorithm/mst/UF.cpp -o CMakeFiles/mst.dir/UF.cpp.s

CMakeFiles/mst.dir/main.cpp.o: CMakeFiles/mst.dir/flags.make
CMakeFiles/mst.dir/main.cpp.o: ../main.cpp
CMakeFiles/mst.dir/main.cpp.o: CMakeFiles/mst.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/ztzhu/courses/Princeton-Algorithm/mst/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/mst.dir/main.cpp.o"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT CMakeFiles/mst.dir/main.cpp.o -MF CMakeFiles/mst.dir/main.cpp.o.d -o CMakeFiles/mst.dir/main.cpp.o -c /home/ztzhu/courses/Princeton-Algorithm/mst/main.cpp

CMakeFiles/mst.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/mst.dir/main.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/ztzhu/courses/Princeton-Algorithm/mst/main.cpp > CMakeFiles/mst.dir/main.cpp.i

CMakeFiles/mst.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/mst.dir/main.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/ztzhu/courses/Princeton-Algorithm/mst/main.cpp -o CMakeFiles/mst.dir/main.cpp.s

CMakeFiles/mst.dir/mst.cpp.o: CMakeFiles/mst.dir/flags.make
CMakeFiles/mst.dir/mst.cpp.o: ../mst.cpp
CMakeFiles/mst.dir/mst.cpp.o: CMakeFiles/mst.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/ztzhu/courses/Princeton-Algorithm/mst/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Building CXX object CMakeFiles/mst.dir/mst.cpp.o"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT CMakeFiles/mst.dir/mst.cpp.o -MF CMakeFiles/mst.dir/mst.cpp.o.d -o CMakeFiles/mst.dir/mst.cpp.o -c /home/ztzhu/courses/Princeton-Algorithm/mst/mst.cpp

CMakeFiles/mst.dir/mst.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/mst.dir/mst.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/ztzhu/courses/Princeton-Algorithm/mst/mst.cpp > CMakeFiles/mst.dir/mst.cpp.i

CMakeFiles/mst.dir/mst.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/mst.dir/mst.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/ztzhu/courses/Princeton-Algorithm/mst/mst.cpp -o CMakeFiles/mst.dir/mst.cpp.s

CMakeFiles/mst.dir/shortest_path.cpp.o: CMakeFiles/mst.dir/flags.make
CMakeFiles/mst.dir/shortest_path.cpp.o: ../shortest_path.cpp
CMakeFiles/mst.dir/shortest_path.cpp.o: CMakeFiles/mst.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/ztzhu/courses/Princeton-Algorithm/mst/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Building CXX object CMakeFiles/mst.dir/shortest_path.cpp.o"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT CMakeFiles/mst.dir/shortest_path.cpp.o -MF CMakeFiles/mst.dir/shortest_path.cpp.o.d -o CMakeFiles/mst.dir/shortest_path.cpp.o -c /home/ztzhu/courses/Princeton-Algorithm/mst/shortest_path.cpp

CMakeFiles/mst.dir/shortest_path.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/mst.dir/shortest_path.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/ztzhu/courses/Princeton-Algorithm/mst/shortest_path.cpp > CMakeFiles/mst.dir/shortest_path.cpp.i

CMakeFiles/mst.dir/shortest_path.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/mst.dir/shortest_path.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/ztzhu/courses/Princeton-Algorithm/mst/shortest_path.cpp -o CMakeFiles/mst.dir/shortest_path.cpp.s

# Object files for target mst
mst_OBJECTS = \
"CMakeFiles/mst.dir/UF.cpp.o" \
"CMakeFiles/mst.dir/main.cpp.o" \
"CMakeFiles/mst.dir/mst.cpp.o" \
"CMakeFiles/mst.dir/shortest_path.cpp.o"

# External object files for target mst
mst_EXTERNAL_OBJECTS =

mst: CMakeFiles/mst.dir/UF.cpp.o
mst: CMakeFiles/mst.dir/main.cpp.o
mst: CMakeFiles/mst.dir/mst.cpp.o
mst: CMakeFiles/mst.dir/shortest_path.cpp.o
mst: CMakeFiles/mst.dir/build.make
mst: CMakeFiles/mst.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/ztzhu/courses/Princeton-Algorithm/mst/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_5) "Linking CXX executable mst"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/mst.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/mst.dir/build: mst
.PHONY : CMakeFiles/mst.dir/build

CMakeFiles/mst.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/mst.dir/cmake_clean.cmake
.PHONY : CMakeFiles/mst.dir/clean

CMakeFiles/mst.dir/depend:
	cd /home/ztzhu/courses/Princeton-Algorithm/mst/build && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/ztzhu/courses/Princeton-Algorithm/mst /home/ztzhu/courses/Princeton-Algorithm/mst /home/ztzhu/courses/Princeton-Algorithm/mst/build /home/ztzhu/courses/Princeton-Algorithm/mst/build /home/ztzhu/courses/Princeton-Algorithm/mst/build/CMakeFiles/mst.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/mst.dir/depend


cmake_minimum_required(VERSION 3.12)

project(sorting)

set(CMAKE_CXX_STANDARD 14)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -O2 -Wall -Werror")

include_directories(${CMAKE_SOURCE_DIR})
file(GLOB SRC_LIST RELATIVE ${CMAKE_SOURCE_DIR} "*.cpp" "*.h")
list(FILTER SRC_LIST EXCLUDE REGEX "tests.cpp")
file(GLOB TEST_SRC_LIST RELATIVE ${CMAKE_SOURCE_DIR} "*.cpp" "*.h")
list(FILTER TEST_SRC_LIST EXCLUDE REGEX "main.cpp")

find_package(fmt REQUIRED)

include(FetchContent)
FetchContent_Declare(
  googletest
  URL https://github.com/google/googletest/archive/03597a01ee50ed33e9dfd640b249b4be3799d395.zip
)
# For Windows: Prevent overriding the parent project's compiler/linker settings
set(gtest_force_shared_crt ON CACHE BOOL "" FORCE)
FetchContent_MakeAvailable(googletest)

enable_testing()

add_executable(${PROJECT_NAME} ${SRC_LIST})
add_executable(${PROJECT_NAME}_test ${TEST_SRC_LIST})

target_link_libraries(
        ${PROJECT_NAME} PRIVATE
        fmt
)
target_link_libraries(
    ${PROJECT_NAME}_test PRIVATE
    fmt
    GTest::gtest_main
)

include(GoogleTest)
gtest_discover_tests(${PROJECT_NAME}_test)

add_custom_target(
    run
    COMMAND "${CMAKE_SOURCE_DIR}/build/${PROJECT_NAME}"
    DEPENDS ${PROJECT_NAME}
)

cmake_minimum_required(VERSION 3.12)

project(llrb)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -O2 -Wall -Werror")

include_directories(${CMAKE_SOURCE_DIR})
file(GLOB SRC_LIST RELATIVE ${CMAKE_SOURCE_DIR} "*.cpp" "*.h")

find_package(fmt REQUIRED)

add_executable(${PROJECT_NAME} ${SRC_LIST})
target_link_libraries(
    ${PROJECT_NAME} PRIVATE
    fmt
)

add_custom_target(
    run
    COMMAND "${CMAKE_SOURCE_DIR}/build/${PROJECT_NAME}"
    DEPENDS ${PROJECT_NAME}
)

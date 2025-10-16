cmake_minimum_required(VERSION 3.16)

project(ColorSelectorApp VERSION 1.0 LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# Busca el paquete de Qt6.
find_package(Qt6 REQUIRED COMPONENTS Widgets)

# Habilita el Meta-Object Compiler (MOC) de Qt de forma automática.
# Esto es necesario para procesar la macro Q_OBJECT y el sistema de señales/slots.
set(CMAKE_AUTOMOC ON)

add_executable(ColorSelectorApp
  src/main.cpp
  src/ColorSelectorWindow.h
  src/ColorSelectorWindow.cpp
)

target_link_libraries(ColorSelectorApp PRIVATE Qt6::Widgets)

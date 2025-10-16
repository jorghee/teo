### Instrucciones de Compilación y Ejecución en Arch Linux

Para compilar y ejecutar este proyecto, sigue los pasos desde la 
terminal

1.  **Instalar dependencias**: Asegúrate de tener `cmake`, `make`,
    `gcc` y `qt6-base` instalados.
    ```bash
    sudo pacman -Syu base-devel cmake qt6-base
    ```

2.  **Preparar el directorio de compilación**: Es una buena práctica
    compilar fuera del directorio de código fuente.
    ```bash
    cd ColorSelectorApp
    
    # Crea y entra a un directorio para la compilación
    mkdir build && cd build
    ```

3.  **Configurar el proyecto con CMake**:
    ```bash
    cmake ..
    ```
    Este comando leerá el `CMakeLists.txt` del directorio padre (`..`)
    y generará los `Makefiles`.

4.  **Compilar el proyecto**:
    ```bash
    make
    ```
    Este comando compilará el código fuente y creará el ejecutable
    `ColorSelectorApp`.

5.  **Ejecutar la aplicación**:
    ```bash
    ./ColorSelectorApp
    ```

Al ejecutarlo, verás una ventana con una lista de colores. Al hacer
clic en cualquiera de ellos, el texto en la parte inferior se
actualizará para reflejar tu selección.

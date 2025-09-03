# Examen de entrada Tecnología de objetos

***Profesor:*** Ing. Richart Smith Escobedo Quispe <br>
***Estudiante:*** Mamani Huarsaya, Jorge Luis

## Parte 1

### Solución problema 1

En C++ se usa el concepto de cabeceras para poder comunicar
diferentes archivos de código fuente (es una buena práctica de
programación). Por ello, creamos la cabecera
[`student.h`](./src/student.h) que define la clase `Student`.

Luego, implementamos la clase `Student` en el archivo
[`student.cpp` ](./src/student.cpp) y finalmente usamos la
cabecera en el archivo [`me.cpp`](./src/me.cpp) para presentarnos.

## Parte 2

### Solución problema 1

Los sistemas operativos Linux usan **GCC** por defecto como un
paquete de compiladores para varios lenguajes de programación.

> Invocar un compilador C++ desde la terminal
~~~
g++ <file.cpp> [options] <file.out>
~~~


### Solución problema 2

Para compilar los archivos relacionados a `Student` usamos el
siguiente comando con algunas opciones.

> `g++` llama al compilador C++ y `-o` es de `output`  
~~~
g++ student.cpp me.cpp -o me.out
~~~

### Solución problema 3

Usamos `./` para decirle a la shell que ejecute el archivo ubicado
en el directorio actual.

~~~
./me.out
~~~

## Parte 3

### Solución problema 1 y 2

```cpp
int invertir(int num) {
  int rev = 0;
  while (num > 0) {
    int n = num % 10;
    rev = rev * 10 + n;
    num /= 10;
  }
  return rev;
}
```

## Parte 4

### Solución problema 1

Los estándares de código son una serie de reglas definidas
para un lenguaje de programación, o bien, un estilo de
programación específico.

### Solución problema 2

![Alan Kay](https://i0.wp.com/imgs.hipertextual.com/wp-content/uploads/2011/10/Alan_Kay.jpg?fit=800%2C533&quality=70&strip=all&ssl=1)

### Solución problema 3

Los editores de texto plano manejan solo caracteres sin formato, es
decir, texto puro. Además son caracteres codificados en ASCII o UTF-8.
En cambio, los editores de texto enriquecidos guardan información
del formato como metadatos y es poco legible.

### Solución problema 4

El tabulador es configurable, por defecto en varios editores de
texto plano es de 4 espacios. Sin embargo personalmente **se usa 
tabulador configurado a 2 espacios**

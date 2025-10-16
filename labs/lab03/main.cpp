#include <iostream>
#include <string>
#include <vector>
#include <memory>

class Horario {
private:
  std::string dia;
  std::string horaInicio;
  std::string horaFin;

public:
  Horario(std::string dia, std::string horaInicio, std::string horaFin)
      : dia(dia), horaInicio(horaInicio), horaFin(horaFin) {}

  std::string getDia() const { return dia; }
  std::string getHoraInicio() const { return horaInicio; }
  std::string getHoraFin() const { return horaFin; }

  void setDia(std::string dia) { this->dia = dia; }
  void setHoraInicio(std::string horaInicio) { this->horaInicio = horaInicio; }
  void setHoraFin(std::string horaFin) { this->horaFin = horaFin; }

  std::string toString() const {
      return "Horario: " + dia + " de " + horaInicio + " a " + horaFin;
  }
};

class Persona {
protected:
  std::string nombre;
  int edad;

public:
  Persona(std::string nombre, int edad) : nombre(nombre), edad(edad) {}
  
  virtual ~Persona() {} 

  std::string getNombre() const { return nombre; }
  int getEdad() const { return edad; }

  void setNombre(std::string nombre) { this->nombre = nombre; }
  void setEdad(int edad) { this->edad = edad; }

  virtual std::string toString() const {
      return "Nombre: " + nombre + ", Edad: " + std::to_string(edad);
  }
};

class Profesor : public Persona {
private:
    std::string especialidad;

public:
  Profesor(std::string nombre, int edad, std::string especialidad)
      : Persona(nombre, edad), especialidad(especialidad) {}

  std::string getEspecialidad() const { return especialidad; }
  void setEspecialidad(std::string especialidad) { this->especialidad = especialidad; }

  std::string toString() const override {
    return "Profesor -> " + Persona::toString() + ", Especialidad: " + especialidad;
  }
};

class Estudiante : public Persona {
private:
  std::string carrera;
  int semestre;

public:
  Estudiante(std::string nombre, int edad, std::string carrera, int semestre)
      : Persona(nombre, edad), carrera(carrera), semestre(semestre) {}

  std::string getCarrera() const { return carrera; }
  int getSemestre() const { return semestre; }
  void setCarrera(std::string carrera) { this->carrera = carrera; }
  void setSemestre(int semestre) { this->semestre = semestre; }

  std::string toString() const override {
      return "Estudiante -> " + Persona::toString() + ", Carrera: " + carrera + ", Semestre: " + std::to_string(semestre);
  }
};

class Curso {
private:
  std::string nombreCurso;
  std::string codigo;
  std::unique_ptr<Horario> horario;

public:
    Curso(std::string nombreCurso, std::string codigo, std::string dia, std::string horaInicio, std::string horaFin)
        : nombreCurso(nombreCurso), codigo(codigo) {
        horario = std::make_unique<Horario>(dia, horaInicio, horaFin);
    }

    std::string getNombreCurso() const { return nombreCurso; }
    std::string getCodigo() const { return codigo; }
    Horario* getHorario() const { return horario.get(); } // Devuelve un puntero crudo para inspección

    std::string toString() const {
        return "Curso: " + nombreCurso + " (" + codigo + ")\n  -> " + horario->toString();
    }
};

class Universidad {
private:
  std::string nombre;
  std::vector<Curso*> cursos;

public:
  Universidad(std::string nombre) : nombre(nombre) {}

  // Método para agregar un curso (recibe un puntero)
  void agregarCurso(Curso* curso) {
    cursos.push_back(curso);
  }

  // Método para imprimir
  std::string toString() const {
    std::string info = "Universidad: " + nombre + "\nCursos Ofertados:\n";
    for (const auto& curso : cursos) {
      info += "- " + curso->getNombreCurso() + " (" + curso->getCodigo() + ")\n";
    }
    return info;
  }
};

class Reporte {
public:
  void generarReporte(const Estudiante& estudiante) const {
    std::cout << "--- Reporte de Estudiante ---" << std::endl;
    std::cout << "Nombre:   " << estudiante.getNombre() << std::endl;
    std::cout << "Carrera:  " << estudiante.getCarrera() << std::endl;
    std::cout << "Semestre: " << estudiante.getSemestre() << std::endl;
    std::cout << "-----------------------------" << std::endl;
  }
};

int main() {
  std::cout << "====== Creando Actores del Sistema ======" << std::endl;

  Profesor prof1("Dr. Alan Turing", 41, "Ciencias de la Computación");
  Profesor prof2("Dra. Grace Hopper", 85, "Sistemas de Información");
  Estudiante est1("Ada Lovelace", 20, "Ingeniería de Software", 3);
  Estudiante est2("Charles Babbage", 22, "Ingeniería Mecatrónica", 5);
  Estudiante est3("John von Neumann", 21, "Matemáticas Aplicadas", 4);

  std::cout << prof1.toString() << std::endl;
  std::cout << prof2.toString() << std::endl;
  std::cout << est1.toString() << std::endl;
  std::cout << est2.toString() << std::endl;
  std::cout << est3.toString() << std::endl;

  std::cout << "\n====== Creando Cursos y Universidad ======" << std::endl;

  Curso* curso1 = new Curso("Programación Orientada a Objetos", "CS102", "Lunes y Miércoles", "10:00", "11:30");
  Curso* curso2 = new Curso("Estructuras de Datos", "CS201", "Martes y Jueves", "14:00", "15:30");

  std::cout << curso1->toString() << std::endl;
  std::cout << curso2->toString() << std::endl;

  Universidad miUniversidad("Universidad Tecnológica de C++");
  miUniversidad.agregarCurso(curso1);
  miUniversidad.agregarCurso(curso2);

  std::cout << "\n" << miUniversidad.toString() << std::endl;

  std::cout << "\n====== Generando Reporte ======" << std::endl;

  Reporte reporteador;
  reporteador.generarReporte(est1);

  delete curso1;
  delete curso2;

  return 0;
}

#include "student.h"

Student::Student(int cui, const std::string &name,
    const std::string &surname_1, const std::string &surname_2)
    : cui(cui), name(name), surname_1(surname_1), surname_2(surname_2) {}

int Student::getCui() const {
  return cui;
}

std::string Student::getName() const {
  return name;
}

std::string Student::getSurname_1() const {
  return surname_1;
}

std::string Student::getSurname_2() const {
  return surname_2;
}

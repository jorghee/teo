#include <iostream>
#include "student.h"

int main() {
  Student student(20230488, "Jorge Luis", "Mamani", "Huarsaya");

  std::cout << student.getCui() << "-" << student.getSurname_1() <<
    "/" << student.getSurname_2() << "," << student.getName()
    << std::endl;
 
  return 0;
}

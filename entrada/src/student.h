#include <string>

class Student {
private:
  int cui;
  std::string name;
  std::string surname_1;
  std::string surname_2;

public:
  Student(int cui, const std::string &name,
      const std::string &surname_1, const std::string &surname_2);

  int getCui() const;
  std::string getName() const;
  std::string getSurname_1() const;
  std::string getSurname_2() const;
};

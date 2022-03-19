public class Person {
  String name; int age;
  Person(String xName, int xAge) {
     name = xName; age = xAge; 
  }
  public String toString() {
    return("(" + name + "," + age + ") ");  
  }
}

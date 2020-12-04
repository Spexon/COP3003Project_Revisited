public class Employee {

  StringBuilder name = new StringBuilder();
  String username;
  String password;
  String email;

  public Employee(String firstName, String surName, String password) {
    name.append(firstName).append(" ").append(surName);

    if (checkName()) {
      setUsername(name.toString());
      setEmail(name.toString(), false);
    } else {
      setUsername("d efault");
      setEmail("user", true);
    }
    if (isValidPassword(password)) {
      this.password = password;

    }
    else {
      this.password = "pw";
    }
    System.out.println(this.password);
    System.out.println(this.username);
  }

  private void setUsername(String name) {
    username = (name.charAt(0) + name.substring(name.indexOf(" "))).toLowerCase().strip();
  }

  private boolean checkName() {
    boolean retVal = false;
    if (name.toString().contains(" ")) {
      retVal = true;
    }
    return retVal;
  }

  private void setEmail(String name, boolean defaultEmail) {
    if (defaultEmail) {
      email = name + "@oracleacademy.Test";
    } else {
      email = (name.substring(0, name.indexOf(" ")) + "." + name.substring(name.indexOf(" ")))
          .toLowerCase() + "@oracleacademy.Test";
    }
  }

  private boolean isValidPassword(String password) {
    boolean validPass = false;
    if (password.matches("[a-zA-Z.@%_$]")) {
      validPass = true;
      System.out.println("TRUE");
    }

    return validPass;
  }

}

public class Employee {
    private int id;
    private String name;
    private double salary;
    private Department department;

    public Employee(int id, String name, double salary, Department department){
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }

    public Department getDepartment() { return department; }

    @Override
    public String toString() {
        return  id + "\t" + name + "\t" + salary + "\t" + department;



    }
}



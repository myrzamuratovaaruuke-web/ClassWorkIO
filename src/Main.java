import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Employee> employeeMap = new HashMap<>();
        File f = new File("src/emp.txt");

        // Step 2: чтение и парсинг
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length != 4) {
                    System.out.println("Строка " + lineNumber + ": неверный формат -> " + line);
                    continue;
                }
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    double salary = Double.parseDouble(parts[2].trim());
                    Department department = Department.valueOf(parts[3].trim().toUpperCase());

                    employeeMap.put(id, new Employee(id, name, salary, department));
                } catch (NumberFormatException e) {
                    System.out.println("Строка " + lineNumber + ": некорректное число -> " + line);
                } catch (IllegalArgumentException e) {
                    System.out.println("Строка " + lineNumber + ": неизвестный отдел -> " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл " + f.getPath() + " не найден.");
            return;
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            return;
        }

        // Step 3: сортировка
        List<Employee> employeeList = new ArrayList<>(employeeMap.values());
        employeeList.sort(Comparator.comparingDouble(Employee::getSalary));
        // другие варианты:
        // employeeList.sort(Comparator.comparing(Employee::getName));
        // employeeList.sort(Comparator.comparingInt(Employee::getId));

        // Запись в sorted_emp.txt
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/sorted_emp.txt"))) {
            for (Employee emp : employeeList) {
                bw.write(String.format("%d,%s,%.1f,%s", emp.getId(), emp.getName(),
                        emp.getSalary(), emp.getDepartment()));
                bw.newLine();
            }
            System.out.println("Готово: данные записаны в sorted_emp.txt");
        } catch (IOException e) {
            System.out.println("Ошибка записи файла: " + e.getMessage());
        }
    }
}





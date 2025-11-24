package ua.opnu;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


public class Main {


    /**
     * Завдання 1: Предикат для перевірки, чи є число простим.
     */
    public static Predicate<Integer> isPrimePredicate() {
        return (i) -> {
            if (i <= 1) {
                return false;
            }
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    return false;
                }
            }
            return true;
        };
    }

    /**
     * Завдання 2.2: Метод фільтрації масиву студентів за допомогою Predicate.
     */
    public static Student[] filterStudents(Student[] input, Predicate<Student> p) {
        List<Student> resultList = new ArrayList<>();
        for (Student s : input) {
            if (p.test(s)) {
                resultList.add(s);
            }
        }
        return resultList.toArray(new Student[0]);
    }

    /**
     * Завдання 3.1: Метод фільтрації за двома умовами (Predicate AND Predicate).
     */
    public static Student[] filterStudentsByTwoConditions(Student[] input, Predicate<Student> p1, Predicate<Student> p2) {
        List<Student> resultList = new ArrayList<>();
        for (Student s : input) {
            // Елемент проходить, якщо задовольняє p1 І p2
            if (p1.test(s) && p2.test(s)) {
                resultList.add(s);
            }
        }
        return resultList.toArray(new Student[0]);
    }

    /**
     * Завдання 4.2: Метод forEach, який приймає масив та поведінку (Consumer),
     * що виконується для кожного елемента.
     */
    public static void forEach(Student[] input, Consumer<Student> action) {
        for (Student s : input) {
            action.accept(s);
        }
    }

    /**
     * Завдання 5.1: Метод, що виконує дію (Consumer) тільки для елементів,
     * що задовольняють умові (Predicate).
     */
    public static void processIf(int[] input, Predicate<Integer> condition, Consumer<Integer> action) {
        for (int i : input) {
            if (condition.test(i)) {
                action.accept(i);
            }
        }
    }

    /**
     * Завдання 6.2: Метод обробки масиву чисел, що застосовує логіку перетворення (Function).
     */
    public static int[] processIntArray(int[] input, Function<Integer, Integer> function) {
        int[] result = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = function.apply(input[i]);
        }
        return result;
    }

    /**
     * Завдання 7.1: Метод перетворення масиву чисел на масив рядків (Stringify).
     */
    public static String[] stringify(int[] input, Function<Integer, String> function) {
        String[] result = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = function.apply(input[i]);
        }
        return result;
    }


    public static void main(String[] args) {


        // Тестові дані для студентів
        Student[] students = {
                new Student("Іван Петренко", "КН-101", new int[]{85, 90, 75}),
                new Student("Олена Сидорова", "КН-101", new int[]{55, 95, 80}), // Заборгованість
                new Student("Андрій Коваленко", "КН-102", new int[]{70, 60, 60}),
                new Student("Марія Шевчук", "КН-102", new int[]{40, 50, 60}) // Заборгованість
        };

        Predicate<Integer> isPrime = isPrimePredicate();
        System.out.println(" Завдання 1: Прості числа");
        System.out.println("7 просте? " + isPrime.test(7));
        System.out.println("10 просте? " + isPrime.test(10));


        Predicate<Student> hasDebtPredicate = student -> {
            for (int mark : student.getMarks()) {
                if (mark < 60) {
                    return false; // Студент має заборгованість, НЕ проходить фільтр
                }
            }
            return true;
        };

        System.out.println("\n Завдання 2: Фільтрація студентів");
        System.out.println("Всі студенти: " + Arrays.toString(students));
        Student[] goodStudents = filterStudents(students, hasDebtPredicate);
        System.out.println("Студенти без заборгованостей: " + Arrays.toString(goodStudents));

        Predicate<Student> isGroupKN101 = student -> student.getGroup().equals("КН-101");

        System.out.println("\n Завдання 3: Фільтрація за двома умовами");
        // Без заборгованостей І група КН-101
        Student[] kn101GoodStudents = filterStudentsByTwoConditions(students, hasDebtPredicate, isGroupKN101);
        System.out.println("Студенти КН-101 без заборгованостей: " + Arrays.toString(kn101GoodStudents));


        Consumer<Student> printStudentFullName = student -> {
            String[] parts = student.getName().split(" ");
            String firstName = parts.length > 0 ? parts[0] : "";
            String lastName = parts.length > 1 ? parts[1] : "";

            System.out.println(lastName.toUpperCase() + " " + firstName);
        };

        System.out.println("\n Завдання 4: Consumer для студентів");
        forEach(students, printStudentFullName);


        Predicate<Integer> isPositive = i -> i > 0;
        Predicate<Integer> isEven = i -> i % 2 == 0;
        Consumer<Integer> printSquared = i -> System.out.println("Квадрат числа " + i + ": " + (i * i));

        int[] numbers = {-5, 2, 8, -3, 11, 4, 0};

        System.out.println("\n Завдання 5: Process If (Predicate + Consumer)");
        System.out.println("Парні та позитивні числа з масиву та їх квадрати:");

        // Об'єднаний предикат
        Predicate<Integer> isPositiveAndEven = isPositive.and(isEven);

        processIf(numbers, isPositiveAndEven, printSquared);

        Function<Integer, Integer> powerOfTwo = n -> (int) Math.pow(2, n);

        int[] inputNumbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        System.out.println("\n Завдання 6: Function (2^n)");
        System.out.println("Вхідний масив: " + Arrays.toString(inputNumbers));

        int[] powers = processIntArray(inputNumbers, powerOfTwo);
        System.out.println("Результат (2^n): " + Arrays.toString(powers));

        Function<Integer, String> numberToString = n -> {
            switch (n) {
                case 0: return "нуль";
                case 1: return "один";
                case 2: return "два";
                case 3: return "три";
                case 4: return "чотири";
                case 5: return "п'ять";
                case 6: return "шість";
                case 7: return "сім";
                case 8: return "вісім";
                case 9: return "дев'ять";
                default: return "невідоме";
            }
        };

        int[] inputDigits = {3, 1, 4, 5, 9, 2, 6, 8, 7, 0};

        System.out.println("\n Завдання 7: Function (Число в Рядок)");
        System.out.println("Вхідний масив: " + Arrays.toString(inputDigits));

        String[] strings = stringify(inputDigits, numberToString);
        System.out.println("Результат (Рядкове представлення): " + Arrays.toString(strings));
    }
}
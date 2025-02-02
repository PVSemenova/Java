import java.util.Scanner;

// 1. Сиракузская последовательность
class SyracuseSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Задание 1:");
        System.out.print("Ввод: ");
        int n = scanner.nextInt();
        int steps = 0;
        while (n != 1) {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n = 3 * n + 1;
            }
            steps++;
        }
        System.out.println("Вывод: " + steps);
    }
}

// 2. Сумма ряда
class SumOfSeries {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Задание 2:");
        System.out.print("Ввод: ");
        int n = scanner.nextInt();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            sum += (i % 2 == 0) ? num : -num;
        }
        System.out.println("Вывод: " + sum);
    }
}

// 3. Ищем клад
class TreasureHunt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Задание 3:");
        System.out.print("Ввод\n");
        int targetX = scanner.nextInt();
        int targetY = scanner.nextInt();
        scanner.nextLine();
        int x = 0, y = 0, steps = 0;
        while (true) {
            String direction = scanner.nextLine();
            if (direction.equals("стоп")) {
                break;
            }
            int distance = Integer.parseInt(scanner.nextLine());
            if (x == targetX && y == targetY) {
                continue;
            }
            steps++;
            switch (direction) {
                case "север": y += distance; break;
                case "юг": y -= distance; break;
                case "восток": x += distance; break;
                case "запад": x -= distance; break;
            }
        }
        System.out.println("Вывод\n" + steps);
        scanner.close();
    }
}

// 4. Логистический максимин
class LogisticMaximin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Задание 4:");
        System.out.print("Ввод: ");
        int roads = scanner.nextInt();
        int bestRoad = 0, maxHeight = 0;
        for (int i = 1; i <= roads; i++) {
            int tunnels = scanner.nextInt();
            int minTunnelHeight = Integer.MAX_VALUE;
            for (int j = 0; j < tunnels; j++) {
                minTunnelHeight = Math.min(minTunnelHeight, scanner.nextInt());
            }
            if (minTunnelHeight > maxHeight) {
                maxHeight = minTunnelHeight;
                bestRoad = i;
            }
        }
        System.out.println("Вывод: " + bestRoad + " " + maxHeight);
    }
}

// 5. Дважды четное число
class DoubleEvenNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Задание 5:");
        System.out.print("Ввод: ");
        int num = scanner.nextInt();
        int d1 = num / 100, d2 = (num % 100) / 10, d3 = num % 10;
        int sum = d1 + d2 + d3;
        int product = d1 * d2 * d3;
        System.out.println("Вывод: " + (sum % 2 == 0 && product % 2 == 0 ? "Является" : "Не является"));
    }
}

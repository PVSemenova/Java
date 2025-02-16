import java.util.*;

// 1. Найти наибольшую подстроку без повторяющихся символов
class LongestUniqueSubstring {
    public static String findLongestUniqueSubstring(String s) {
        int[] lastIndex = new int[128];
        Arrays.fill(lastIndex, -1);
        int left = 0, maxLength = 0, start = 0;
        for (int right = 0; right < s.length(); right++) {
            if (lastIndex[s.charAt(right)] >= left) {
                left = lastIndex[s.charAt(right)] + 1;
            }
            lastIndex[s.charAt(right)] = right;
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                start = left;
            }
        }
        return s.substring(start, start + maxLength);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Задание 1:");
        System.out.print("Ввод: ");
        String input = scanner.nextLine();
        System.out.println("Вывод: " + findLongestUniqueSubstring(input));
    }
}

// 2. Объединить два отсортированных массива
class MergeSortedArrays {
    public static int[] merge(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) {
            result[k++] = arr1[i] < arr2[j] ? arr1[i++] : arr2[j++];
        }
        while (i < arr1.length) result[k++] = arr1[i++];
        while (j < arr2.length) result[k++] = arr2[j++];
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Задание 2:");
        int[] arr1 = {1, 3, 5};
        int[] arr2 = {2, 4, 6};
        int[] merged = merge(arr1, arr2);
        System.out.println("Вывод: " + Arrays.toString(merged));
    }
}

// 3. Найти максимальную сумму подмассива
class MaxSubarraySum {
    public static int findMaxSum(int[] nums) {
        int maxSum = nums[0], currentSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println("Задание 3:");
        int[] nums = {-5, -2, 3, 10, -1};
        System.out.println("Вывод: " + findMaxSum(nums));
    }
}

// 4. Повернуть массив на 90 градусов по часовой стрелке
class RotateMatrixClockwise {
    public static int[][] rotate(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] rotated = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotated[j][rows - 1 - i] = matrix[i][j];
            }
        }
        return rotated;
    }
    public static void main(String[] args) {
        System.out.println("Задание 4:");
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}};
        System.out.println("Вывод: " + Arrays.deepToString(rotate(matrix)));
    }
}

// 5. Найти пару элементов в массиве, сумма которых равна заданному числу
class TargetSum {
    public static int[] findPair(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{nums[i], nums[j]};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Задание 5:");
        int[] nums = {1, 2, 7, 5};
        int target = 9;
        System.out.println("Вывод: " + Arrays.toString(findPair(nums, target)));
    }
}

// 6. Найти сумму всех элементов в двумерном массиве
class SumOfMatrix {
    public static int sum(int[][] matrix) {
        int k = 0;
        for (int[] row : matrix) {
            for (int num : row) {
                k += num;
            }
        }
        return k;
    }

    public static void main(String[] args) {
        System.out.println("Задание 6:");
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Вывод: " + sum(matrix));
    }
}

// 7. Найти максимальный элемент в каждой строке двумерного массива
class MaxInEachRow {
    public static int[] findMaxInRows(int[][] matrix) {
        int[] maxValues = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            maxValues[i] = Arrays.stream(matrix[i]).max().getAsInt();
        }
        return maxValues;
    }

    public static void main(String[] args) {
        System.out.println("Задание 7:");
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Вывод: " + Arrays.toString(findMaxInRows(matrix)));
    }
}

// 8. Повернуть двумерный массив на 90 градусов против часовой стрелке
class RotateMatrixCounterclockwise {
    public static int[][] rotate(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] rotated = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotated[cols - 1 - j][i] = matrix[i][j];
            }
        }
        return rotated;
    }

    public static void main(String[] args) {
        System.out.println("Задание 8:");
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}};
        System.out.println("Вывод: " + Arrays.deepToString(rotate(matrix)));
    }
}
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {


        assertReverse(new Object[]{"1", "2", "3", "4"}, new Object[]{"4", "3", "2", "1"});
        assertReverse(new Object[]{"1", "2", "3"}, new Object[]{"3", "2", "1"});
        assertReverse(new Object[]{}, new Object[]{});
        assertReverse(new Object[]{"1"}, new Object[]{"1"});
        assertReverse(null, null);

    }

    private static void assertReverse(Object[] actual, Object[] expected) {

        reverse(actual);

        boolean equals = Arrays.equals(actual, expected);
        System.out.println("Test reversing is ok: " + equals);
    }

    public static void reverse(Object[] arr) {

        if (arr == null) {
            return;
        }

        int len = arr.length;
        for (int i = 0; i < len / 2 ; i++) {

            Object tmp = arr[i];
            arr[i] = arr[len - i - 1];
            arr[len - i - 1] = tmp;

        }

    }
}

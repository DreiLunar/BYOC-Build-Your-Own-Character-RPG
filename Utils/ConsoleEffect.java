package Utils;

public class ConsoleEffect {

    public static final int FAST = 10;
    public static final int NORMAL = 40;
    public static final int SLOW = 100;

    /**
     * Prints text character-by-character
     * @param text The string to print
     * @param speedMillis How fast to type
     */
    public static void type(String text, int speedMillis) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(speedMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    public static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
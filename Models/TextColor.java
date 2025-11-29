package Models;

public class TextColor {
    // Reset
    public static final String RESET = "\033[0m";

    // Regular Colors
    public static final String BLACK = "\033[0;30m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String WHITE = "\033[0;37m";

    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";
    public static final String RED_BOLD = "\033[1;31m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String YELLOW_BOLD = "\033[1;33m";
    public static final String BLUE_BOLD = "\033[1;34m";
    public static final String PURPLE_BOLD = "\033[1;35m";
    public static final String CYAN_BOLD = "\033[1;36m";
    public static final String WHITE_BOLD = "\033[1;37m";

    // Custom colors
    public static final String ORANGE = "\033[38;5;208m"; // Bright orange

    // Color methods for specific use cases
    public static String player(String text) {
        return GREEN + text + RESET;
    }
    
    public static String enemy(String text) {
        return RED + text + RESET;
    }
    
    public static String damage(String text) {
        return YELLOW + text + RESET; // Player damage
    }
    
    public static String enemyDamage(String text) {
        return PURPLE + text + RESET; // Enemy damage - ORANGE
    }
    
    public static String heal(String text) {
        return GREEN_BOLD + text + RESET;
    }
    
    public static String status(String text) {
        return CYAN + text + RESET;
    }
    
    public static String dialogue(String text) {
        return WHITE + text + RESET;
    }
    
    public static String important(String text) {
        return PURPLE_BOLD + text + RESET;
    }
    
    public static String warning(String text) {
        return RED_BOLD + text + RESET;
    }
    
    public static String success(String text) {
        return GREEN_BOLD + text + RESET;
    }
    
    public static String blue(String text) {
        return BLUE + text + RESET;
    }
    
    public static String defense(String text) {
        return BLUE + text + RESET;
    }
}
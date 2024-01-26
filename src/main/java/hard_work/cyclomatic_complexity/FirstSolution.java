package hard_work.cyclomatic_complexity;

public class FirstSolution {
    public static void sourceMethod(int n) {
        // Check if n is positive
        if (n > 0) {
            // Check if n is even
            if (n % 2 == 0) {
                // Check if n is divisible by 4
                if (n % 4 == 0) {
                    // Check if n is divisible by 8
                    if (n % 8 == 0) {
                        // Check if n is divisible by 16
                        if (n % 16 == 0) {
                            // Check if n is divisible by 32
                            if (n % 32 == 0) {
                                System.out.println("n is divisible by 32");
                            } else {
                                System.out.println("n is divisible by 16 but not by 32");
                            }
                        } else {
                            System.out.println("n is divisible by 8 but not by 16");
                        }
                    } else {
                        System.out.println("n is divisible by 4 but not by 8");
                    }
                } else {
                    System.out.println("n is even but not divisible by 4");
                }
            } else {
                // Check if n is divisible by 3
                if (n % 3 == 0) {
                    // Check if n is divisible by 9
                    if (n % 9 == 0) {
                        System.out.println("n is divisible by 9");
                    } else {
                        System.out.println("n is divisible by 3 but not by 9");
                    }
                } else {
                    System.out.println("n is odd but not divisible by 3");
                }
            }
        } else {
            System.out.println("n is not positive");
        }
    }

    public static void refactoredMethod(int n) {
        if (n <= 0) {
            System.out.println("n is not positive");
            return;
        }

        if (n % 2 != 0) {
            checkDivisibilityByThree(n);
            return;
        }

        checkDivisibilityByPowersOfTwo(n);
    }

    private static void checkDivisibilityByThree(int n) {
        if (n % 3 != 0) {
            System.out.println("n is odd but not divisible by 3");
            return;
        }

        System.out.println(n % 9 == 0 ? "n is divisible by 9" : "n is divisible by 3 but not by 9");
    }

    private static void checkDivisibilityByPowersOfTwo(int n) {
        if (n % 32 == 0) {
            System.out.println("n is divisible by 32");
            return;
        }
        if (n % 16 == 0) {
            System.out.println("n is divisible by 16 but not by 32");
            return;
        }
        if (n % 8 == 0) {
            System.out.println("n is divisible by 8 but not by 16");
            return;
        }
        if (n % 4 == 0) {
            System.out.println("n is divisible by 4 but not by 8");
            return;
        }
        System.out.println("n is even but not divisible by 4");
    }
}

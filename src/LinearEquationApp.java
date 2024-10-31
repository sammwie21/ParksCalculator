import java.util.Scanner;
public class LinearEquationLogic {
    private Scanner s = new Scanner(System.in);
    public void start() {
        System.out.println("Welcome to the Linear Equation Calculator!");
        loop();
        System.out.println("Goodbye!");
    }

    private void loop(){
        boolean continueLoop = true;
        while (continueLoop) {
            int[] point1 = getPoint("Enter the first point (x1, y1): ");
            int[] point2 = getPoint("Enter the second point (x2, y2): ");

            System.out.println("The points are (" + point1[0] + ", " + point1[1] + ") and " + "(" + point2[0] + ", " + point2[1] + ")");
            if (point1[0] == point2[0] && point1[1] == point2[1]) {
                System.out.println("The points are on the same spot and do not create a line");
                System.out.println("The distance of the points is 0");
            }
            else if (point1[0] == point2[0]) {
                System.out.println("The points form a vertical line, and the slope is undefined.");
                System.out.println("The equation of the line is: x = " + point1[0]);
                System.out.println("The distance of the points is: " + Math.abs(point1[1]-point2[1]));
            } else {
                LinearEquation equation = new LinearEquation(point1[0], point1[1], point2[0], point2[1]);
                System.out.println(equation.lineInfo());
                System.out.print("Enter an x value: ");
                while (!s.hasNextDouble()) {
                    System.out.println("Invalid x value. Please enter a valid number.");
                    System.out.print("Enter an x value: ");
                    s.next();
                }
                double x = s.nextDouble();
                System.out.println("The corresponding point on the line is: " + equation.coordinateForX(x));

                s.nextLine();
            }
            System.out.print("Would you like to calculate another equation (yes/no)? ");
            continueLoop = s.nextLine().equalsIgnoreCase("yes");
        }
    }

    private int[] getPoint(String prompt) {
        System.out.print(prompt);
        String input = s.nextLine().trim();

        if (input.startsWith("(") && input.endsWith(")") && input.contains(",")) {
            input = input.substring(1, input.length() - 1);  // Remove parentheses
            String[] parts = input.split(",");  // Split on the comma
            try {
                int x = Integer.parseInt(parts[0].trim());
                int y = Integer.parseInt(parts[1].trim());
                return new int[]{x, y};
            } catch (NumberFormatException e) {
                System.out.println("Invalid format. Please enter integers for the coordinates.");
            }
        } else {
            System.out.println("Invalid input. Please enter coordinates in the form (x, y).");
        }
        return getPoint(prompt);
    }
}

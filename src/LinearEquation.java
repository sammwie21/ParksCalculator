public class LinearEquation {
    private int x1, y1, x2, y2;
    private double slope;
    private double yIntercept;

    public LinearEquation(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.slope = (double)(y2 - y1) / (x2 - x1);
        this.yIntercept = y1 - slope * x1;
    }

    public double distance() {
        return roundedToHundredth(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }

    public double slope() {
        double slope = roundedToHundredth((double) (y2 - y1) / (x2 - x1));
        return slope;
    }

    public double yIntercept() {
        return roundedToHundredth(y1 - slope() * x1);
    }

    public String equation() {
        double intercept = yIntercept();
        String yInterceptStr = formatYIntercept();

        String slopeStr = simplifySlope();
        if(slopeStr.equals("0") && intercept != 0){
            return "y = " + yInterceptStr;
        }else if(slopeStr.equals("1") && intercept == 0){
            return "y = x";
        }else if(!slopeStr.equals("0") && intercept == 0){
            return "y = " + slopeStr + "x";
        }else if(slopeStr.equals("1") && intercept != 0){
            return "y = x + " + yInterceptStr;
        }else if(slopeStr.equals("-1") && intercept != 0){
            return "y = -x + " + yInterceptStr;
        }

        return "y = " + slopeStr + "x " + yInterceptStr.substring(0, 1) + " " + yInterceptStr.substring(1);
    }

    public String coordinateForX(double x) {
        double y = slope() * x + yIntercept();
        return "(" + roundedToHundredth(x) + ", " + roundedToHundredth(y) + ")";
    }

    public String lineInfo() {
        String yInterceptStr = formatYIntercept();
        return  "The equation of the line between these points is: " + equation() + "\n" +
                "The slope of this line is: " + slope() + "\n" +
                "The y-intercept of this line is: " + yInterceptStr + "\n" +
                "The distance between these points is: " + distance();
    }

    private String formatYIntercept() {
        yIntercept = roundedToHundredth(yIntercept);
        if (yIntercept < 0) {
            return String.valueOf(yIntercept); // Return as is for negative values
        } else {
            return "+" + yIntercept; // Add '+' for positive values
        }
    }

    private String simplifySlope() {
        int numerator = ((y1-y2) * 100); // Assuming a precision of two decimal places
        int denominator = ((x1-x2) *(100));

        int gcd = gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;

        if(denominator < 0){
            denominator *= -1;
            numerator *= -1;
        }

        if (denominator == 1) {
            return String.valueOf(numerator); // Return as integer if denominator is 1
        } else {
            return numerator + "/" + denominator; // Return as fraction
        }
    }

    private double roundedToHundredth(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return Math.abs(a);
    }
}

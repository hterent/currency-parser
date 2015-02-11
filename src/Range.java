import java.text.DecimalFormat;

/**
 * Created by anna on 11/02/15.
 */
class Range {
    private double low, high;


    public Range(double low, double high){
        this.low = low;
        this.high = high;
    }


    public String toString(){
        String strLow = "--",
                strHigh = "--";

        DecimalFormat decimalFormat = new DecimalFormat("##.##");

        strLow = low < 0 ? strLow : decimalFormat.format(low);
        strHigh = high < 0 ? strHigh : decimalFormat.format(high);

        return String.format("%s - %s", strLow, strHigh);
    }
}
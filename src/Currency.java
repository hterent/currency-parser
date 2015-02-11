import java.text.DecimalFormat;

/**
 * Created by anna on 10/02/15.
 */
class Currency {
    private double nbu;
    private String title;
    private Range optimal, average;


    private Currency(){}


    private class NullCurrency extends Currency{
        public NullCurrency(){
            setTitle("--");
            setNbu(-1);
            setOptimal(new Range(-1, -1));
            setAverage(new Range(-1, -1));
        }
    }


    public Currency(String title, double nbu, Range optimal, Range average){
        new NullCurrency();
        setTitle(title);
        setNbu(nbu);
        setOptimal(optimal);
        setAverage(average);
    }


    public String toString(){
        String strNbu = nbu < 0 ? "--" : String.valueOf(nbu);

        return this.title + "\t|\t" + new DecimalFormat("##.##").format(nbu) +
                       "\t|\t" + this.optimal.toString() +
                       "\t|\t" + this.average.toString();
    }


    public void setTitle(String title) { this.title = title; }


    public void setOptimal(Range optimal) { this.optimal = optimal; }


    public void setAverage(Range average) { this.average = average; }


    public void setNbu(double nbu) { this.nbu = nbu; }
}
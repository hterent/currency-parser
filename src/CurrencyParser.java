import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


/**
 * Created by anna on 10/02/15.
 */
class CurrencyParser {
    private static final String PLN_URL =
            "http://tables.finance.ua/ru/currency/cash/~/ua/pln/0#2:0";

    private static final String USD_URL =
            "http://tables.finance.ua/ru/currency/cash/~/ua/usd/0#2:0";

    private static final String EUR_URL =
            "http://tables.finance.ua/ru/currency/cash/~/ua/eur/0#2:0";

    private static final String RUB_URL =
            "http://tables.finance.ua/ru/currency/cash/~/ua/rub/0#2:0";


    public static void main(String[] args) throws IOException {
        String usage = "Look up exchange rates for UAH from finance.ua\n\n" +
                       "Usage: java -jar currencyParser.jar <currency>...\n"+
                       "\tAvailable arguments:\n\n"+
                       "\t\tusd, eur, rub, pln, all";

        boolean valid = false;


        if (args.length == 0)
            valid = false;

        for (String s : args)
            if (s.equals("all") || s.equals("usd") || s.equals("eur") ||
                    s.equals("rub") || s.equals("pln")) {
                valid = true;
            }
            else
                valid =  false;


        System.out.println();

        if (valid) {
            System.out.println("title\t|\tNBU\t|\toptimal\t\t|\taverage");
            System.out.println("======== =============== ======================= ====================");

            new CurrencyParser().printResult(args);
        }
        else
            System.out.println(usage);

        System.out.println();
    }


    private Currency findCurrency(String title) throws IOException {
        String url = "";

        if (title.equalsIgnoreCase("pln"))
            url = PLN_URL;
        else if (title.equalsIgnoreCase("usd"))
            url = USD_URL;
        else if (title.equalsIgnoreCase("eur"))
            url = EUR_URL;
        else if (title.equalsIgnoreCase("rub"))
            url = RUB_URL;


        double[] values = getValues(url);

        return new Currency(title.toUpperCase(), values[4],
                                new Range(values[0], values[1]),
                                new Range(values[2], values[3]));
    }


    private double[] getValues(String url) throws IOException {
        double[] values = new double[5];

        // instead of null
        for(double d : values)
            d = -1;

        Document doc = Jsoup.connect(url).timeout(30000).get();
        Elements table = doc.getElementsByClass("naliktable").last().getElementsByClass("price");

        for (int i = 0; i < 5; i++)
            values[i] = Double.parseDouble(table.get(i).text());

        return values;
    }


    private void printResult(String[] args) throws IOException {
        for (String c : args)
            if (c.equals("all")) {
                printResult(new String[]{"usd", "eur", "rub", "pln"});
                break;
            }
            else
                System.out.println(findCurrency(c).toString());
    }
}
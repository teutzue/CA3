package XmlProcessor;

import deploy.DeploymentConfiguration;
import entity.CurrencyCode;
import entity.DailyRate;
import java.io.IOException;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author CosticaTeodor
 */
public class XmlReaderDemo extends DefaultHandler implements Runnable {

    static XmlReaderDemo xml = new XmlReaderDemo();
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

    // static ArrayList<CurrencyCode> currencies = new ArrayList<>();

    Date date = new Date();
    int count = 0;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start Document (Sax-event)");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End Document (Sax-event)");
    }

    @Override
    public void run() {
        System.out.println("hello from xmlReaderDemo");
        try {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            xr.setContentHandler(xml);
            URL url = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en");
            xr.parse(new InputSource(url.openStream()));

        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

    } // End of run

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        DailyRate dailyrate = new DailyRate();
        CurrencyCode currencycode = new CurrencyCode();

        if (attributes.getQName(0).equals("id")) {
            String datestring = attributes.getValue(0);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = formatter.parse(datestring);
            } catch (ParseException ex) {
                Logger.getLogger(XmlReaderDemo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (attributes.getQName(0) != null) {
            if (attributes.getQName(0).equals("code")) {
                String codes = attributes.getValue(0);
                System.out.println("The codes  is " + attributes.getValue(0));
                currencycode.setCode(codes);
                System.out.println("code vv : " + currencycode.getCode());
            }
        }
        if (attributes.getQName(1) != null) {
            if (attributes.getQName(1).equals("desc")) {
                String desc = attributes.getValue(1);
                currencycode.setDescription(desc);
            }
        }
        if (attributes.getQName(2) != null && !attributes.getValue(2).equals("-") && !attributes.getValue(2).equals("DKK")) {

            if (attributes.getQName(2).equals("rate")) {
                String rate = attributes.getValue(2);
                double value = Double.parseDouble(rate);
                dailyrate.setRate(value);
            }
        }

        dailyrate.setDate(date);
        currencycode.addDailyRates(dailyrate);
        dailyrate.setCurrencyCode(currencycode);
        
        EntityManager em = emf.createEntityManager();
        if (currencycode.isValid() && dailyrate.isValid()) {

            System.out.println(currencycode.getCode());
           
                CurrencyCode cc = em.find(CurrencyCode.class, currencycode.getCode());
                
                if(cc != null) {
                    cc.addDailyRates(dailyrate);
                    currencycode = cc;
                } else {
                    
                }
            
            try {
//                count++;
//                System.out.println("the counter " + count);
                em.getTransaction().begin();
                em.persist(currencycode);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
        }
////        //when persist sets the date
    }

    public static void main(String[] argv) {
        try {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            xr.setContentHandler(xml);
            URL url = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en");
            xr.parse(new InputSource(url.openStream()));

        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    } // End of method

} // End of class

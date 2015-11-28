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
public class XmlReaderDemo extends DefaultHandler {
    static XmlReaderDemo xml = new XmlReaderDemo();
     
    static ArrayList<CurrencyCode> currencies = new ArrayList<>();
    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start Document (Sax-event)");
    }
    @Override
    public void endDocument() throws SAXException {
        System.out.println("End Document (Sax-event)");
    }
    Date date = new Date();
    int count = 0;
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
//                dailyrate.se you dont need to do that
//                dailyrate.setDailyratesCode(codes);
                System.out.println("The codes  is "+attributes.getValue(0));
                currencycode.setCode(codes);
               System.out.println("code vv : " + currencycode.getCode());
            }
        }
        if (attributes.getQName(1) != null) {
            if (attributes.getQName(1).equals("desc")) {
                String desc = attributes.getValue(1);
                currencycode.setDescription(desc);
                //System.out.println("asdasdasd" + attributes.getValue(1));
            }
        }
        if (attributes.getQName(2) != null && !attributes.getValue(2).equals("-") && !attributes.getValue(2).equals("DKK")) {
//            System.out.println(attributes.getValue(2));
            if (attributes.getQName(2).equals("rate")) {
                String rate = attributes.getValue(2);
                double value = Double.parseDouble(rate);
                dailyrate.setRate(value);
            }
        }
//        System.out.println("");
        //list.add(code);
       dailyrate.setDate(date);
       currencycode.addDailyRates(dailyrate);
       dailyrate.setCurrencyCode(currencycode);
//        currencies.add(currencycode);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
        EntityManager em = emf.createEntityManager();
        if(currencycode.isValid() && dailyrate.isValid())
        {
            
        try {
            count++;
        System.out.println("the counter "+count);
//            System.out.println("ghjdjdh" + code.getDailyratesCode());
            em.getTransaction().begin();
            em.persist(currencycode);
            em.getTransaction().commit();
        } finally {
            em.close();
            emf.close();
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
            
//            EntityManagerFactory emf = Persistence.createEntityManagerFactory("testXMlManytoOnePU");
//            EntityManager em = emf.createEntityManager();
//            try {
//                em.getTransaction().begin();
//                for (CurrencyCode argv1 : currencies) {
//                    em.persist(argv1);
//                }
//                em.getTransaction().commit();
//            } finally {
//                em.close();
//                emf.close();
//            }
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}

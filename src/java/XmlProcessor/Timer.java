package XmlProcessor;

import XmlProcessor.XmlReaderDemo;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author CosticaTeodor
 */
@WebListener
public class Timer implements ServletContextListener{

    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        
        XmlReaderDemo xmlReaderDemo = new XmlReaderDemo();
        scheduler = Executors.newSingleThreadScheduledExecutor();
//        scheduler.scheduleAtFixedRate(xmlReaderDemo, 60, 15, TimeUnit.SECONDS);
        scheduler.scheduleWithFixedDelay(xmlReaderDemo, 60, 15, TimeUnit.DAYS);
        xmlReaderDemo.run();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        scheduler.shutdownNow();
    }
}

package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class DailyRate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
     
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    
    private Double rate;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private CurrencyCode currencycode;
    
    public void setCurrencyCode(CurrencyCode cc)
    {
     currencycode = cc;
    }
     public boolean isValid() {
       return date!=null && rate !=null && currencycode!=null; 
       
    }
    public int getid() {
        return id;
    }
    
  public CurrencyCode getCur() {
        return currencycode;
    }
    
    public Date getDate() {
        return date;
    }
    public double getRate() {
        return rate;
    }
    public void setCode(int id) {
        this.id = id;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }
    public DailyRate() {
    }
    
    
    
    
}

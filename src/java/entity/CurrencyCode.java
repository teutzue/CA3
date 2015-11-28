/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
/**
 *
 * @author Yoana
 */
@Entity
public class CurrencyCode implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String code;
    private String description;
    @OneToMany(mappedBy = "currencycode", cascade = CascadeType.PERSIST)
    @JoinColumn
    private List<DailyRate> dailyRates;
    public void addDailyRates(DailyRate cc) {
        if (dailyRates == null) {
            dailyRates = new ArrayList<>();
        }
        dailyRates.add(cc);
        cc.setCurrencyCode(this);
    }
    public boolean isValid() {
        return code != null &&  description!= null && dailyRates != null;
    }
    public String getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    public void setCode(String code) {
        if (code != null) {
            this.code = code;
        } else {
            System.out.println("Code is null");
        }
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public CurrencyCode() {
    }
}

package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CosticaTeodor
 */
@Entity
@Table(name = "currency_code")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CurrencyCode.findAll", query = "SELECT c FROM CurrencyCode c"),
    @NamedQuery(name = "CurrencyCode.findByValue", query = "SELECT c FROM CurrencyCode c WHERE c.value = :value"),
    @NamedQuery(name = "CurrencyCode.findByDate", query = "SELECT c FROM CurrencyCode c WHERE c.date = :date"),
    @NamedQuery(name = "CurrencyCode.findByDailyratesCode", query = "SELECT c FROM CurrencyCode c WHERE c.dailyratesCode = :dailyratesCode")})
public class CurrencyCode implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Value")
    private double value;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private String date;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "dailyrates_Code")
    private String dailyratesCode;
    @ManyToOne
    @PrimaryKeyJoinColumn
    private Dailyrates dailyrates;
    

    public CurrencyCode() {
    }

    public CurrencyCode(String dailyratesCode) {
        this.dailyratesCode = dailyratesCode;
    }

    public CurrencyCode(String dailyratesCode, double value, String date) {
        this.dailyratesCode = dailyratesCode;
        this.value = value;
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDailyratesCode() {
        return dailyratesCode;
    }

    public void setDailyratesCode(String dailyratesCode) {
        this.dailyratesCode = dailyratesCode;
    }

    public Dailyrates getDailyrates() {
        return dailyrates;
    }

    public void setDailyrates(Dailyrates dailyrates) {
        this.dailyrates = dailyrates;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dailyratesCode != null ? dailyratesCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CurrencyCode)) {
            return false;
        }
        CurrencyCode other = (CurrencyCode) object;
        if ((this.dailyratesCode == null && other.dailyratesCode != null) || (this.dailyratesCode != null && !this.dailyratesCode.equals(other.dailyratesCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CurrencyCode[ dailyratesCode=" + dailyratesCode + " ]";
    }

}

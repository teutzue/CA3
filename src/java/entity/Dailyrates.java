
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CosticaTeodor
 */
@Entity
@Table(name = "dailyrates")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dailyrates.findAll", query = "SELECT d FROM Dailyrates d"),
    @NamedQuery(name = "Dailyrates.findByCode", query = "SELECT d FROM Dailyrates d WHERE d.code = :code"),
    @NamedQuery(name = "Dailyrates.findByDescription", query = "SELECT d FROM Dailyrates d WHERE d.description = :description")})
public class Dailyrates implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "Code")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "dailyrates")
    private List<CurrencyCode> currencyCodes;
    
    public void addCurrencyCode(CurrencyCode cc){
        if (currencyCodes == null) {
            currencyCodes = new ArrayList<>();
        }
        currencyCodes.add(cc);
        cc.setDailyrates(this);
    }

    public Dailyrates() {
    }

    public Dailyrates(String code) {
        this.code = code;
    }

    public Dailyrates(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CurrencyCode> getCurrencyCode() {
        return currencyCodes;
    }

    public void setCurrencyCode(List<CurrencyCode> currencyCode) {
        this.currencyCodes = currencyCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dailyrates)) {
            return false;
        }
        Dailyrates other = (Dailyrates) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Dailyrates[ code=" + code + " ]";
    }

}

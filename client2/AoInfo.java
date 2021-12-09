
package client2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for aoInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="aoInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="regNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chassis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isNew" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="KW" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aoInfo", propOrder = {
    "regNum",
    "chassis",
    "isNew",
    "kw"
})
public class AoInfo {

    protected String regNum;
    protected String chassis;
    protected boolean isNew;
    @XmlElement(name = "KW")
    protected int kw;

    /**
     * Gets the value of the regNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegNum() {
        return regNum;
    }

    /**
     * Sets the value of the regNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegNum(String value) {
        this.regNum = value;
    }

    /**
     * Gets the value of the chassis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChassis() {
        return chassis;
    }

    /**
     * Sets the value of the chassis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChassis(String value) {
        this.chassis = value;
    }

    /**
     * Gets the value of the isNew property.
     * 
     */
    public boolean isIsNew() {
        return isNew;
    }

    /**
     * Sets the value of the isNew property.
     * 
     */
    public void setIsNew(boolean value) {
        this.isNew = value;
    }

    /**
     * Gets the value of the kw property.
     * 
     */
    public int getKW() {
        return kw;
    }

    /**
     * Sets the value of the kw property.
     * 
     */
    public void setKW(int value) {
        this.kw = value;
    }

}

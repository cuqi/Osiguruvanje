
package client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getHouseholdQuotation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getHouseholdQuotation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HouseholdInfo" type="{http://myservice/}householdInfo" minOccurs="0"/>
 *         &lt;element name="sessionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getHouseholdQuotation", propOrder = {
    "householdInfo",
    "sessionID"
})
public class GetHouseholdQuotation {

    @XmlElement(name = "HouseholdInfo")
    protected HouseholdInfo householdInfo;
    protected String sessionID;

    /**
     * Gets the value of the householdInfo property.
     * 
     * @return
     *     possible object is
     *     {@link HouseholdInfo }
     *     
     */
    public HouseholdInfo getHouseholdInfo() {
        return householdInfo;
    }

    /**
     * Sets the value of the householdInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link HouseholdInfo }
     *     
     */
    public void setHouseholdInfo(HouseholdInfo value) {
        this.householdInfo = value;
    }

    /**
     * Gets the value of the sessionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionID() {
        return sessionID;
    }

    /**
     * Sets the value of the sessionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionID(String value) {
        this.sessionID = value;
    }

}

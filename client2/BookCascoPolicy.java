
package client2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bookCascoPolicy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bookCascoPolicy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CascoInfo" type="{http://myservice/}cascoInfo" minOccurs="0"/>
 *         &lt;element name="Insured" type="{http://myservice/}insuredInfo" minOccurs="0"/>
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
@XmlType(name = "bookCascoPolicy", propOrder = {
    "cascoInfo",
    "insured",
    "sessionID"
})
public class BookCascoPolicy {

    @XmlElement(name = "CascoInfo")
    protected CascoInfo cascoInfo;
    @XmlElement(name = "Insured")
    protected InsuredInfo insured;
    protected String sessionID;

    /**
     * Gets the value of the cascoInfo property.
     * 
     * @return
     *     possible object is
     *     {@link CascoInfo }
     *     
     */
    public CascoInfo getCascoInfo() {
        return cascoInfo;
    }

    /**
     * Sets the value of the cascoInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CascoInfo }
     *     
     */
    public void setCascoInfo(CascoInfo value) {
        this.cascoInfo = value;
    }

    /**
     * Gets the value of the insured property.
     * 
     * @return
     *     possible object is
     *     {@link InsuredInfo }
     *     
     */
    public InsuredInfo getInsured() {
        return insured;
    }

    /**
     * Sets the value of the insured property.
     * 
     * @param value
     *     allowed object is
     *     {@link InsuredInfo }
     *     
     */
    public void setInsured(InsuredInfo value) {
        this.insured = value;
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

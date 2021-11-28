
package soapService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for converter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="converter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EUR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "converter", propOrder = {
    "eur"
})
public class Converter {

    @XmlElement(name = "EUR")
    protected String eur;

    /**
     * Gets the value of the eur property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEUR() {
        return eur;
    }

    /**
     * Sets the value of the eur property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEUR(String value) {
        this.eur = value;
    }

}


package soapService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cascoInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cascoInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="typeCasco" type="{http://myservice/}typeCasco" minOccurs="0"/>
 *         &lt;element name="windows" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="vehiclePrice" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="typeValue" type="{http://myservice/}typeValue" minOccurs="0"/>
 *         &lt;element name="franchise" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cascoInfo", propOrder = {
    "typeCasco",
    "windows",
    "vehiclePrice",
    "typeValue",
    "franchise"
})
public class CascoInfo {

    @XmlSchemaType(name = "string")
    protected TypeCasco typeCasco;
    protected boolean windows;
    protected int vehiclePrice;
    @XmlSchemaType(name = "string")
    protected TypeValue typeValue;
    protected int franchise;

    /**
     * Gets the value of the typeCasco property.
     * 
     * @return
     *     possible object is
     *     {@link TypeCasco }
     *     
     */
    public TypeCasco getTypeCasco() {
        return typeCasco;
    }

    /**
     * Sets the value of the typeCasco property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeCasco }
     *     
     */
    public void setTypeCasco(TypeCasco value) {
        this.typeCasco = value;
    }

    /**
     * Gets the value of the windows property.
     * 
     */
    public boolean isWindows() {
        return windows;
    }

    /**
     * Sets the value of the windows property.
     * 
     */
    public void setWindows(boolean value) {
        this.windows = value;
    }

    /**
     * Gets the value of the vehiclePrice property.
     * 
     */
    public int getVehiclePrice() {
        return vehiclePrice;
    }

    /**
     * Sets the value of the vehiclePrice property.
     * 
     */
    public void setVehiclePrice(int value) {
        this.vehiclePrice = value;
    }

    /**
     * Gets the value of the typeValue property.
     * 
     * @return
     *     possible object is
     *     {@link TypeValue }
     *     
     */
    public TypeValue getTypeValue() {
        return typeValue;
    }

    /**
     * Sets the value of the typeValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeValue }
     *     
     */
    public void setTypeValue(TypeValue value) {
        this.typeValue = value;
    }

    /**
     * Gets the value of the franchise property.
     * 
     */
    public int getFranchise() {
        return franchise;
    }

    /**
     * Sets the value of the franchise property.
     * 
     */
    public void setFranchise(int value) {
        this.franchise = value;
    }

}

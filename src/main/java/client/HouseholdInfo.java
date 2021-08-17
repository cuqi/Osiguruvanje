
package client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for householdInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="householdInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="typeObject" type="{http://myservice/}typeObject" minOccurs="0"/>
 *         &lt;element name="areaOfObject" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="dateOfObject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="typeHouseholdCover" type="{http://myservice/}typeHouseholdCover" minOccurs="0"/>
 *         &lt;element name="contractLenght" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "householdInfo", propOrder = {
    "typeObject",
    "areaOfObject",
    "dateOfObject",
    "typeHouseholdCover",
    "contractLenght"
})
public class HouseholdInfo {

    @XmlSchemaType(name = "string")
    protected TypeObject typeObject;
    protected float areaOfObject;
    protected String dateOfObject;
    @XmlSchemaType(name = "string")
    protected TypeHouseholdCover typeHouseholdCover;
    protected int contractLenght;

    /**
     * Gets the value of the typeObject property.
     * 
     * @return
     *     possible object is
     *     {@link TypeObject }
     *     
     */
    public TypeObject getTypeObject() {
        return typeObject;
    }

    /**
     * Sets the value of the typeObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeObject }
     *     
     */
    public void setTypeObject(TypeObject value) {
        this.typeObject = value;
    }

    /**
     * Gets the value of the areaOfObject property.
     * 
     */
    public float getAreaOfObject() {
        return areaOfObject;
    }

    /**
     * Sets the value of the areaOfObject property.
     * 
     */
    public void setAreaOfObject(float value) {
        this.areaOfObject = value;
    }

    /**
     * Gets the value of the dateOfObject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateOfObject() {
        return dateOfObject;
    }

    /**
     * Sets the value of the dateOfObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateOfObject(String value) {
        this.dateOfObject = value;
    }

    /**
     * Gets the value of the typeHouseholdCover property.
     * 
     * @return
     *     possible object is
     *     {@link TypeHouseholdCover }
     *     
     */
    public TypeHouseholdCover getTypeHouseholdCover() {
        return typeHouseholdCover;
    }

    /**
     * Sets the value of the typeHouseholdCover property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeHouseholdCover }
     *     
     */
    public void setTypeHouseholdCover(TypeHouseholdCover value) {
        this.typeHouseholdCover = value;
    }

    /**
     * Gets the value of the contractLenght property.
     * 
     */
    public int getContractLenght() {
        return contractLenght;
    }

    /**
     * Sets the value of the contractLenght property.
     * 
     */
    public void setContractLenght(int value) {
        this.contractLenght = value;
    }

}

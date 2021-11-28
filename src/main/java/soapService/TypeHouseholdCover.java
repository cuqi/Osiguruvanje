
package soapService;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for typeHouseholdCover.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="typeHouseholdCover">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="STANDARD"/>
 *     &lt;enumeration value="COMFORT"/>
 *     &lt;enumeration value="MEGA"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "typeHouseholdCover")
@XmlEnum
public enum TypeHouseholdCover {

    STANDARD,
    COMFORT,
    MEGA;

    public String value() {
        return name();
    }

    public static TypeHouseholdCover fromValue(String v) {
        return valueOf(v);
    }

}

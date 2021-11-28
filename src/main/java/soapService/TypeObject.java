
package soapService;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for typeObject.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="typeObject">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="HOUSE"/>
 *     &lt;enumeration value="APARTMENT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "typeObject")
@XmlEnum
public enum TypeObject {

    HOUSE,
    APARTMENT;

    public String value() {
        return name();
    }

    public static TypeObject fromValue(String v) {
        return valueOf(v);
    }

}


package client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for typeValue.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="typeValue">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="MARKET"/>
 *     &lt;enumeration value="NEW"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "typeValue")
@XmlEnum
public enum TypeValue {

    MARKET,
    NEW;

    public String value() {
        return name();
    }

    public static TypeValue fromValue(String v) {
        return valueOf(v);
    }

}

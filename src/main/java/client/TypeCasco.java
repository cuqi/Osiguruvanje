
package client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for typeCasco.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="typeCasco">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="FULL"/>
 *     &lt;enumeration value="PARTIAL"/>
 *     &lt;enumeration value="BASIC"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "typeCasco")
@XmlEnum
public enum TypeCasco {

    FULL,
    PARTIAL,
    BASIC;

    public String value() {
        return name();
    }

    public static TypeCasco fromValue(String v) {
        return valueOf(v);
    }

}

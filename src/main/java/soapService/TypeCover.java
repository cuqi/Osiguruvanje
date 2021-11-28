
package soapService;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for typeCover.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="typeCover">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="VISA"/>
 *     &lt;enumeration value="CLASSIC"/>
 *     &lt;enumeration value="CLASSIC_PLUS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "typeCover")
@XmlEnum
public enum TypeCover {

    VISA,
    CLASSIC,
    CLASSIC_PLUS;

    public String value() {
        return name();
    }

    public static TypeCover fromValue(String v) {
        return valueOf(v);
    }

}

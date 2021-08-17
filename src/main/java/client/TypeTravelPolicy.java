
package client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for typeTravelPolicy.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="typeTravelPolicy">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="INDIVIDUAL"/>
 *     &lt;enumeration value="FAMILY"/>
 *     &lt;enumeration value="STUDENT"/>
 *     &lt;enumeration value="GROUP"/>
 *     &lt;enumeration value="BUSINESS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "typeTravelPolicy")
@XmlEnum
public enum TypeTravelPolicy {

    INDIVIDUAL,
    FAMILY,
    STUDENT,
    GROUP,
    BUSINESS;

    public String value() {
        return name();
    }

    public static TypeTravelPolicy fromValue(String v) {
        return valueOf(v);
    }

}

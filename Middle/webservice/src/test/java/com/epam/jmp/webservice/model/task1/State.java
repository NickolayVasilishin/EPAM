
package com.epam.jmp.webservice.model.task1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for state.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="state">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="BOOKED"/>
 *     &lt;enumeration value="PAYED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "state")
@XmlEnum
public enum State {

    BOOKED,
    PAYED;

    public String value() {
        return name();
    }

    public static State fromValue(String v) {
        return valueOf(v);
    }

}

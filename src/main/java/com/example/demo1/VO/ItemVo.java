package com.example.demo1.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemVo {
    @XmlElement
    private String xsdState;
    @XmlElement
    private String msg;
    @XmlElement
    private String name;
    @XmlElement
    private Date date;
    @XmlElement
    private BigDecimal price;

}

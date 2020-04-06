package bank;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@Data
@Table(name = "transfers")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "src", "dest"})
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tr_src")
    private long src;

    @Column(name = "tr_dest")
    private long dest;

    private long amount;

    @Column
    private String result;

}

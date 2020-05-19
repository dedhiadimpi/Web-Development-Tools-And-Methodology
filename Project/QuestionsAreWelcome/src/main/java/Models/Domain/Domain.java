/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author dedhi
 */
@Entity
@Table(name = "tbl_domain")
public class Domain {
    @Id
    @Column(name = "domain_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long domain_id;
    @Column(name = "domain_name", length = 50)
    private String domain_name;

    public long getDomain_id() {
        return domain_id;
    }

    public void setDomain_id(long domain_id) {
        this.domain_id = domain_id;
    }

    public String getDomain_name() {
        return domain_name;
    }

    public void setDomain_name(String domain_name) {
        this.domain_name = domain_name;
    }  
    
}

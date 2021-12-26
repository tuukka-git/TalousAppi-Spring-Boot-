
package com.example.demo.type;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;






@Entity
@Table(name = "types")
public class Type {

    @Id
    @Column(name = "type_id")
    private Long type_id;
    
    @Column(name = "name")
    private String name;

    public Type(Long type_id, String name) {
        this.type_id = type_id;
        this.name = name;
    }

    public Long getType_id() {
        return type_id;
    }

    public void setType_id(Long type_id) {
        this.type_id = type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    

   

    

    
}
    


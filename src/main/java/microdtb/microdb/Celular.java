package microdtb.microdb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity // This tells Hibernate to make a table out of this class
public class Celular {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  @Column(unique=true)

  private Integer numero;
  
  private Integer marca;

  private Integer modelo;
  
  @OneToOne(mappedBy = "celular")
  private User user;

  public Integer getId(){
    return id;
  }

  public void setId(Integer id){
    this.id=id;
  }
  
  public Integer getNumero(){
    return numero;
  }

  public void setNumero(Integer numero){
    this.numero=numero;
  }
  public Integer getMarca(){
    return marca;
  }

  public void setMarca(Integer marca){
    this.marca=marca;
  }

   public Integer getModelo(){
    return modelo;
  }

  public void setModelo(Integer modelo){
    this.modelo=modelo;
  }
 
}

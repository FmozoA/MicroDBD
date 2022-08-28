package microdtb.microdb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  @Column(unique=true)

  private Integer cedula;
  
  private String name;

  private String email;

  private String apellido;

  public Integer getCedula(){
    return cedula;
  }

  public void setCedula(Integer cedula) {
    this.cedula = cedula;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) throws Exception{
    //validar formato
    if (!email.contains("@")){
      throw new Exception("El email contiene un formato erroneo");
    }
    this.email = email;
  }

  public String getApellido() {
    return apellido;
  }
  public void setApellido(String apellido) {
    this.apellido = apellido;
  }
  

 
}

package weektwo.mahderf.demo.Controllers;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class EmployeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
   @NotNull
   @Size(min=4, max=20)
    private String name;
   @NotNull
   @Email
   private String emailaddress;
   @NotNull
   @Size(min=5, max=20)
   private String organisation;
   @NotNull
   @Size(min=8, max=10)
   @DateTimeFormat(pattern="MM/dd/yyyy")
   private String startdate;
   @Size(max=10)
   @DateTimeFormat(pattern="MM/dd/yyyy")
   private String enddate;

   private long days;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDays() {
        return days;
    }

    public void setDays(long days) {
        this.days = days;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
}

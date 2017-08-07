package weektwo.mahderf.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import weektwo.mahderf.demo.Repository.EmployeRepository;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/*
 * This application takes Starting date and End date of an employee from a customer input and displays the time worked in terms of days with other informations entered from
 * the customer.
 * @author mahderf
 */

@Controller
public class MainControllers
{

    @Autowired
    EmployeRepository employeRepository;
     // Intializing a date object and a date format
    Date date= new Date();
    DateFormat df=new SimpleDateFormat("MM/dd/yyyy");
     //displays the welcome page
    @GetMapping("/")
    public String firstpage(Model model)
    {
        String newmessage="Welcome to Robo Resume";
        model.addAttribute("message",newmessage);
        return "home";
    }
     //prompts user to enter required fields
    @GetMapping("/enterinformation")
    public String empinfor(Model model)
    {
        model.addAttribute("newinfo", new EmployeInfo());
        return "addinfo";

    }
     // posts the input from the web and also under this method is were we calculate the time worked in Days and add it to the Model
     // Here also the data entered is validated
    @PostMapping("/enterinformation")
    public String postinfo(@Valid @ModelAttribute("newinfo") EmployeInfo otherinfo, BindingResult bindingResult, Model newmodel) {

        EmployeInfo employeInfo =  new EmployeInfo();

        if (bindingResult.hasErrors()) {
            return "addinfo";
        }
        System.out.println(otherinfo.getName());
      //the date entered is parsed and calculated to get the difference between the two dates
      //the if statement puts in the current date if the End date left blank
        try {

           if(otherinfo.getEnddate()!=null)
           {
               otherinfo.setEnddate(df.format(date));
           }

            long test;
            Date date1 = df.parse(otherinfo.getStartdate());
            Date date2 = df.parse(otherinfo.getEnddate());

            long diff = date2.getTime() - date1.getTime();
           test = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            employeInfo.setDays(test);
            otherinfo.setDays(test);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        newmodel.addAttribute("dates", employeInfo);

        employeRepository.save(otherinfo);


        return "resultinfo";
    }
     // this shows the employee data that is already entered in the database
    @GetMapping("/showemployeeinfo")
    public String showallbooks(Model model)
    {
        Iterable<EmployeInfo> employeeinfo=employeRepository.findAll();
        model.addAttribute("dbemployee", employeeinfo);
        return "showemployee";
    }

}
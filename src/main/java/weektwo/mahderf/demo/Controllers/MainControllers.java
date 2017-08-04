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

@Controller
public class MainControllers
{


    @Autowired
    EmployeRepository employeRepository;

    Date date= new Date();
    DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

    @GetMapping("/")
    public String firstpage(Model model)
    {
        String newmessage="Welcome to Robo Resume";
        model.addAttribute("message",newmessage);
        return "home";
    }
    @GetMapping("/enterinformation")
    public String empinfor(Model model)
    {
        model.addAttribute("newinfo", new EmployeInfo());
        return "addinfo";

    }
    @PostMapping("/enterinformation")
    public String postinfo(@Valid @ModelAttribute("newinfo") EmployeInfo otherinfo, BindingResult bindingResult, Model newmodel) {

        EmployeInfo employeInfo =  new EmployeInfo();

        if (bindingResult.hasErrors()) {
            return "addinfo";
        }
        System.out.println(otherinfo.getName());

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

}

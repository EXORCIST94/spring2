package hello;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import java.util.List;


@Controller
public class GreetingController {

    @RequestMapping(value ="/greeting", method = RequestMethod.GET)
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @RequestMapping(value="/greeting", method = RequestMethod.POST)
    public String showGreeting(@ModelAttribute Greeting greeting, Model model){
        long id = greeting.getId();
        String message = greeting.getContent();
        System.out.println("hello !");
        Greeting obj = new Greeting();
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        s.merge(greeting);
        s.getTransaction().commit();

        /*s.get("Greeting",1);
        Greeting greeting1 = s.get(Greeting.class, 10);

        Query query = s.createQuery("select * from Person");
        query.setMaxResults(10);
        List list = query.list();


        int j = new Adder<Integer>().add(1, 2);
        double f = new Adder<Double>().add(1.0, 2.0);

        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add("2");


        ArrayList<Integer> arrayList2 = new ArrayList<>();
        arrayList2.add(1);
        arrayList2.add(2);
        arrayList2.add("33400");

*/      s.close();
        s = sf.openSession();
        s.beginTransaction();
        obj = (Greeting) s.get(Greeting.class,123);
        System.out.println("content:"+ obj.getContent());

        List L = s.createQuery("from Value v").list();
        //Value obj2 = (Value) s.get(Value.class,1);
        if(L.isEmpty())
            System.out.println("Empty list");
        else
        System.out.println("Value:"+ L);
        model.addAttribute("cash",obj);
        model.addAttribute("greeting",greeting);
        s.close();
        return "result";

    }


 /*   private class Adder<T extends Number>  {
        public T add(T v, T v1) {
            return v.add(v1);
        }
    }
*/
}
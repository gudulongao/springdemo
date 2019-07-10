package demo.springdemo.rep.spcs;

import demo.springdemo.bean.Person;
import demo.springdemo.rep.PersonRepository;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class PersonSpecs {
    /**
     * 获取所有成年人
     */
    public static Specification<Person> getAdults() {
        return (Specification<Person>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get
                ("age"), 18);
    }

    public static void main(String[] args) {
        PersonRepository repository = null;
        List<Person> all = repository.findAll(getAdults());
    }
}

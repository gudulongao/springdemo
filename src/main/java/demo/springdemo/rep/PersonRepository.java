package demo.springdemo.rep;

import demo.springdemo.bean.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {
    /**
     * 根据名称查询
     */
    List<Person> findByName(String name);

    /**
     * 根据name查询（支持排序）
     */
    List<Person> findByName(String name, Sort sort);


    @Override
    List<Person> findAll(Specification<Person> specification);

    Page<Person> findByAddress(String address, Pageable pageable);
}

package com.hibernate.crud;

import com.hibernate.classes.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class MyCRUD {
    private SessionFactory sessionFactory;
    private Transaction transaction;

    public MyCRUD(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * save employee
     */

    public Integer saveEmployee(Employee employee) {
        Session session = null;
        Integer id = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            id = (Integer) session.save(employee);
            transaction.commit();
            System.out.println("Saved employee "+employee.getName());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Couldn't to save employee "+employee.getName());
            if(id < 1) {
                System.out.println("Couldn't to save an object");
            }
        } finally {
            session.close();
        }
        return id;
    }

    /**
     * get employee
     */

    public Employee getEmployee(int id) {
        Session session = null;
        Employee employee = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "from Employee e where e.id = " + id;
            Query query = session.createQuery(hql, Employee.class);
            List<Employee> results = query.list();
            if(results.size() > 0) {
                employee = results.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Couldn't to find an employee with id:"+id);
        } finally {
            session.close();
        }

        return employee;
    }

    /**
     * update employee
     */
    public void updateEmployee(int id, String name) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            employee.setName(name);
            session.save(employee);
            transaction.commit();
            System.out.println("Updated employee with id:"+id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Couldn't to update employee with id:"+id);
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    /**
     * delete Employee
     */

    public void deleteEmployee(int id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Employee employee = (Employee) session.get(Employee.class, id);
            session.delete(employee);
            transaction.commit();
            System.out.println("Employee with id:"+id+" has been deleted");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Couldn't to delete Employee with id:"+id);
        } finally {
            session.close();
        }
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    public static void main(String[] args) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        MyCRUD myCRUD = new MyCRUD(sessionFactory);

        Employee employee = new Employee();
        employee.setName("Test");
        employee.setSurname("Testovski");
        employee.setAge(404);
        employee.setAddress("Testostan 17");
        employee.setJobTitle("Tester");
        employee.setSalary(5);

        int id = myCRUD.saveEmployee(employee);
        System.out.println(myCRUD.getEmployee(id));
        myCRUD.updateEmployee(id, "Testoslaw");
        System.out.println(myCRUD.getEmployee(id));
        //myCRUD.deleteEmployee(id);
    }
}

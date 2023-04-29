package com.kuang.dao;

import com.kuang.pojo.Department;
import com.kuang.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//员工
@Repository
public class EmployeeDao {
    //模拟数据库中的数据

    private static Map<Integer, Employee> employees=null;
    //员工有所属的部门
    //thymeleaf模板太强了吧 我也没改dao的id，辛亏有提醒 update页面里面不可以写th:value=$｛｝，否则不错。哭了
    @Autowired
    private DepartmentDao departmentDao;
    static {
        employees=new HashMap <Integer,Employee> ();//创建一个部门表
//departmentDao需要是静态的，然后记得new一下，不然会空指针错误
        employees.put(1001,new Employee(1001,"AA","2473467@qq.com",1,new Department(101,"教学部")));
        employees.put(1002,new Employee(1002,"AA","2473467@qq.com",1,new Department(101,"市场部")));
        employees.put(1003,new Employee(1003,"AA","2473467@qq.com",1,new Department(101,"教研部")));
        employees.put(1004,new Employee(1004,"AA","2473467@qq.com",1,new Department(101,"运营部")));
        employees.put(1005,new Employee(1005,"AA","2473467@qq.com",1,new Department(101,"后勤部")));
    }
    //主键自增
    private static Integer initId = 1006;
    //增加一个员工
    public void save(Employee employee){
        if(employee.getId()==null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
    }

//查询全部员工信息
    public Collection<Employee> getAll(){
        return employees.values();
    }
    //通过ID 查询员工
    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }
//删除员工通过ID
    public void delete(Integer id){
        employees.remove(id);
    }

}

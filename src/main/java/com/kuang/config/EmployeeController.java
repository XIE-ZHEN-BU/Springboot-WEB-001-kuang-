package com.kuang.config;

import com.kuang.dao.DepartmentDao;
import com.kuang.dao.EmployeeDao;
import com.kuang.pojo.Department;
import com.kuang.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmployeeController {
    //
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;
    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //查部门数据信息
        Collection<Department> departments = departmentDao.getDepartment();
        model.addAttribute("departments",departments);
        return "emp/add";
    }
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println("save=>"+employee);
        employeeDao.save(employee);//调用底层业务方法保存员工信息
        //添加的操作
        return "redirect:/emps";
    }
    //去员工的修改页面
    //只显示一行LastName的，在EmployeeDao里看下Map的key和员工的id是否一致
    //感谢那个说给css加/的兄弟，可以显示页面了
    @GetMapping("/emp/{id}")
    public String toUpdateEMP(@PathVariable("id")Integer id,Model model){
        //查出原来的数据
        Employee employee= employeeDao.getEmployeeById(id);
        model.addAttribute("emp",employee);

        return "emp/update";
    }
    //不得不说，那个那个原地转圈的方法确实很妙，有不服者可以不照着他的来，哈哈哈 姓名没有的要加th:value
    //LastName的问题去看EmployeeDao，把key value改为一致即可
    //LastName是你Controller里面没用EmployeeDao，只用了departmentDao update里面没有接收到departments吧
    //LastName的同志 去看看EmployeeDao 是k-v值 是否一致 去controller 修改员工界面 toupdate @path
    @PostMapping ("/updateEmp")
    public String updateEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    //删除员工
    @GetMapping  ("/delemp/{id}")
    public String deleteEmp(@PathVariable("id")int id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }






}

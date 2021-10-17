package com.code.force.controller;

import com.code.force.domain.Users;
import com.code.force.domain.UsersTime;
import com.code.force.model.UsersReport;
import com.code.force.service.ExcelService;
import com.code.force.service.UserService;
import com.code.force.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Controller
public class UserController {

    LocalDate localDate=LocalDate.now();

    @Autowired
    UserService userService;

    @Autowired
    ExcelService excelService;

    @Autowired
    WordService wordService;

    public String downloadFolder="D:/CODING/Spring Boot/force/src/main/webapp";

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public ModelAndView addUser(HttpServletRequest request,@RequestParam("imagePath") MultipartFile imagePath, @RequestParam("documentPath") MultipartFile documentPath) {
        Users user=new Users();
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setUserName(request.getParameter("userName"));
        user.setPassword(request.getParameter("password"));
        user.setRole(request.getParameter("role"));
        user.setBlocked(0);
        userService.saveUser(user,imagePath,documentPath);

        if(user.getRole().equals("STUDENT"))
            return new ModelAndView("redirect:/admin/students");
        else if(user.getRole().equals("ADMINISTRATOR"))
            return new ModelAndView("redirect:/admin/administrators");
        else if(user.getRole().equals("LIBRARIAN"))
            return new ModelAndView("redirect:/admin/librarians");
        else
            return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(value = "/users/edit", method = RequestMethod.POST)
    public ModelAndView editUser(HttpServletRequest request,@RequestParam("imagePath") MultipartFile imagePath, @RequestParam("documentPath") MultipartFile documentPath) {
        Users user=new Users();
        user.setUserID(Long.parseLong(request.getParameter("userID")));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setUserName(request.getParameter("userName"));
        user.setPassword(request.getParameter("password"));
        user.setRole(request.getParameter("role"));
        user.setBlocked(Integer.parseInt(request.getParameter("blocked")));
        user.setImagePath(request.getParameter("imagePath1"));
        user.setDocumentPath(request.getParameter("documentPath1"));

        userService.editUser(user,imagePath,documentPath);

        if(user.getRole().equals("STUDENT"))
            return new ModelAndView("redirect:/admin/students");
        else if(user.getRole().equals("ADMINISTRATOR"))
            return new ModelAndView("redirect:/admin/administrators");
        else if(user.getRole().equals("LIBRARIAN"))
            return new ModelAndView("redirect:/admin/librarians");
        else
            return new ModelAndView("redirect:/admin");
    }
    @GetMapping("/users/excel/download")
    public ResponseEntity downloadUserAsExcel(HttpServletRequest req){
        String role=req.getParameter("role");
        String fileName=role+"S.xlsx";
        InputStreamResource file = new InputStreamResource(excelService.load(role));
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+fileName)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @GetMapping("/users/word/download")
    public ResponseEntity downloadUserAsWordDocument(HttpServletRequest req){
        String role=req.getParameter("role");
        Long userID=Long.parseLong(req.getParameter("userID"));
        String fileName=role+"S.docx";
        Users user=userService.getUserByUserID(userID);
        List<UsersTime> userTime=userService.getUsersTimeByID(userID);
        Integer presentDays=userService.getUserPresentDays(userID);
        Integer missedDays=userService.getUserMissedDays(userID);
        Integer totalHours=userService.getTotalWorkedHours(userID);
        InputStreamResource file = new InputStreamResource(wordService.setMicrosoftWordDocument(user,userTime,presentDays,missedDays,totalHours));
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+fileName)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(file);
    }

    @GetMapping("/users/pdf/download")
    public ResponseEntity downloadUserAsPdfDocument(HttpServletRequest req){
        String role=req.getParameter("role");
        Long userID=Long.parseLong(req.getParameter("userID"));
        String fileName=role+"S.pdf";
        Users user=userService.getUserByUserID(userID);
        List<UsersTime> userTime=userService.getUsersTimeByID(userID);
        Integer presentDays=userService.getUserPresentDays(userID);
        Integer missedDays=userService.getUserMissedDays(userID);
        Integer totalHours=userService.getTotalWorkedHours(userID);
        InputStreamResource file = new InputStreamResource(wordService.setPdfDocument(user,userTime,presentDays,missedDays,totalHours));
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+fileName)
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(file);
    }

    @GetMapping("/users/getAll")
    public ResponseEntity getAllUsers() {
        List<Users> list = userService.getAllUsers();
        System.out.println("Request is processed");

        return ResponseEntity.ok(list);
    }

    @RequestMapping(value = "/users/setTime", method = RequestMethod.POST)
    public ModelAndView setUserTime(HttpServletRequest request){
        System.out.println(request.getContentType());
        String role=request.getParameter("role");
        Long userID=Long.parseLong(request.getParameter("userID"));
        String arrival=request.getParameter("timepickerArrival");
        String leaving=request.getParameter("timepickerLeaving");
        String date=request.getParameter("datePicker");
        System.out.println("Time: "+arrival+" "+userID+"  "+leaving+" "+date);
        UsersTime ut=new UsersTime(userID,arrival,leaving,date);
        userService.setUserTime(ut);
        if(role.equals("STUDENT"))
            return new ModelAndView("redirect:/admin/students");
        else if(role.equals("ADMINISTRATOR"))
            return new ModelAndView("redirect:/admin/administrators");
        else if(role.equals("LIBRARIAN"))
            return new ModelAndView("redirect:/admin/librarians");
        else
            return null;
    }

    @RequestMapping(value = "/users/delete",method = RequestMethod.POST)
    public ModelAndView deleteUserByID(HttpServletRequest request) {
        Long userID=Long.parseLong(request.getParameter("userID"));
        String role=request.getParameter("role");
        userService.delete(userID);
        if(role.equals("STUDENT"))
            return new ModelAndView("redirect:/admin/students");
        else if(role.equals("ADMINISTRATOR"))
            return new ModelAndView("redirect:/admin/administrators");
        else if(role.equals("LIBRARIAN"))
            return new ModelAndView("redirect:/admin/librarians");
        else
            return new ModelAndView("redirect:/admin");
    }

    @PutMapping("/users/update/firstName/{userID}/{firstName}")
    public ResponseEntity updateFirstNameByID(@PathVariable Long userID, @PathVariable String firstName) {
        userService.updateFirstNameByID(userID, firstName);
        return ResponseEntity.ok(HttpServletResponse.SC_OK);
    }

    @PutMapping("/users/update/lastName/{userID}/{lastName}")
    public ResponseEntity updateLastNameByID(@PathVariable Long userID, @PathVariable String lastName) {
        userService.updateLastNameByID(userID, lastName);
        return ResponseEntity.ok(HttpServletResponse.SC_OK);
    }

    @PutMapping("/users/update/userName/{userID}/{userName}")
    public ResponseEntity updateUserNameByID(@PathVariable Long userID, @PathVariable String userName) {
        userService.updateUserNameByID(userID, userName);
        return ResponseEntity.ok(HttpServletResponse.SC_OK);
    }

    @PutMapping("/users/update/password/{userID}/{password}")
    public ResponseEntity updatePasswordByID(@PathVariable Long userID, @PathVariable String password) {
        userService.updatePasswordByID(userID, password);
        return ResponseEntity.ok(HttpServletResponse.SC_OK);
    }

    @PutMapping("/users/update/blocked/{userID}/{blocked}")
    public ResponseEntity updateBlockedByID(@PathVariable Long userID, @PathVariable Integer blocked) {
        userService.updateBlockedByID(userID, blocked);
        return ResponseEntity.ok(HttpServletResponse.SC_OK);
    }

    @PutMapping("/users/update/role/{userID}/{role}")
    public ResponseEntity updateRoleByID(@PathVariable Long userID, @PathVariable String role) {
        userService.updateRoleByID(userID, role);
        return ResponseEntity.ok(HttpServletResponse.SC_OK);
    }


    @RequestMapping("/admin/students")
    public String adminStudents(Model model){
        List<Users> users=userService.getAllByRole("STUDENT");
        List<UsersTime> usersTimes=userService.getAllUsersTime();
        model.addAttribute("usersTimes",usersTimes);
        model.addAttribute("users",users);
        return "admin/adminStudents";
    }

    @RequestMapping("/admin/librarians")
    public String adminLibrarians(Model model){
        List<Users> users=userService.getAllByRole("LIBRARIAN");
        List<UsersTime> usersTimes=userService.getAllUsersTime();
        model.addAttribute("usersTimes",usersTimes);
        model.addAttribute("users",users);
        return "admin/adminLibrarians";
    }

    @RequestMapping("/admin/administrators")
    public String adminAdministrators(Model model){
        List<Users> users=userService.getAllByRole("ADMINISTRATOR");
        List<UsersTime> usersTimes=userService.getAllUsersTime();
        model.addAttribute("usersTimes",usersTimes);
        model.addAttribute("users",users);
        return "admin/adminAdministrators";
    }

    @RequestMapping(value = "/users/userTime", method = RequestMethod.POST)
    public String getUsersTimeByID(HttpServletRequest request, Model model){
        Long userID=Long.parseLong(request.getParameter("userID"));
        List<UsersTime> userTimes= userService.getUsersTimeByID(userID);
        Users user=userService.getUserByUserID(userID);
        model.addAttribute("userTimes",userTimes);
        model.addAttribute("user",user);
        System.out.println("Present Days: "+userService.getUserPresentDays(userID));
        return "/admin/userTimeByID";
    }

    @RequestMapping(value = "/users/usersReport", method = RequestMethod.POST)
    public String getReport(HttpServletRequest request, Model model){
        String order=request.getParameter("order");
        List<UsersReport> usersReport=userService.getUsersReportOrderedBy(order);
        model.addAttribute("usersReport",usersReport);
        return "/admin/usersReport";
    }


    @GetMapping("")
    public String displayLogin(Model model){
        Users user=new Users();
        model.addAttribute("user",user);
        return "signIn";
    }

    @PostMapping("")
    public String processLogin(@ModelAttribute("user") Users user){
        Users user1=userService.userExists(user.getUserName());
        if(user1!=null && user1.getPassword().equals(user.getPassword()))
        {
            if(user1.getRole().equals("STUDENT")) {
                return "student/studentPage";
            }
            else if(user1.getRole().equals("ADMINISTRATOR")) {
                System.out.println("Admin Page");
                return "admin/adminPage";
            }
            else if(user1.getRole().equals("LIBRARIAN")){
                return "libra/librarianPage";
            }
            else{
                return "signIn";
            }
        }else {
            System.out.println("Error");
            return "signIn";
        }
    }
}

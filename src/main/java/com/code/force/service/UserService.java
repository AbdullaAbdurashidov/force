package com.code.force.service;

import com.code.force.domain.Users;
import com.code.force.domain.UsersTime;
import com.code.force.model.UsersReport;
import com.code.force.repository.UserRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UsersTimeService usersTimeService;

    public String uploadFolder="D:/CODING/Spring Boot/force/src/main/webapp/resource/files";
    public String downloadFolder="D:/CODING/Spring Boot/force/src/main/webapp/";

    private final Hashids hashids=new Hashids(getClass().getName(),8);

    public List<UsersTime> getAllUsersTime(){
        return usersTimeService.getAllUsersTime();
    }

    public List<UsersTime> getUsersTimeByID(Long ID){
        return usersTimeService.getUsersTimeByID(ID);
    }

    public Users getUserByUserID(Long userID){
        return userRepository.findByIDQuery(userID);
    }

    public Integer getUserPresentDays(Long userID){
        return usersTimeService.getUserPresentDays(userID);
    }

    public Integer getUserMissedDays(Long userID){
        return usersTimeService.getUserMissedDays(userID);
    }

    public Integer getTotalWorkedHours(Long userID){
        return usersTimeService.getTotalWorkedHours(userID);
    }

    public List<UsersReport> getUsersReportOrderedBy(String order){
        List<UsersReport> usersReports=usersTimeService.getAllUsersReportOrderedBy(order);
        return usersReports;
    }


    public void setUserTime(UsersTime userTime)
    {
        usersTimeService.setUserTime(userTime);
    }


    public Users saveUser(Users user, MultipartFile imagePath, MultipartFile documentPath) {
            Users user1=userRepository.save(user);
                user1.setImagePath(saveFile(imagePath,user.getUserID()));
                user1.setDocumentPath(saveFile(documentPath, user.getUserID()));
            userRepository.save(user);
            return null;
    }

    public Users editUser(Users user, MultipartFile imagePath, MultipartFile documentPath) {
        Users user1=userRepository.save(user);
        if(!imagePath.isEmpty()) {
            deleteFile(user.getImagePath());
            System.out.println("Deleted last image");
            user1.setImagePath(saveFile(imagePath, user.getUserID()));
        }
        if(!documentPath.isEmpty()) {
            deleteFile(user.getDocumentPath());
            System.out.println("Deleted last document");
            user1.setDocumentPath(saveFile(documentPath, user.getUserID()));
        }
        userRepository.save(user1);
        return null;
    }

    public List<Users> getAllByRole(String role){
        return userRepository.findByRoleQuery(role);
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users userExists(String userName){
        Users user=userRepository.userExists(userName);
        return user;
    }



    private String getExtension(String fileName){
        String ext="";
        if(fileName!=null && !fileName.isEmpty()){
            int dot=fileName.lastIndexOf('.');
            if(dot>0&& dot<=fileName.length()-2){
                ext=fileName.substring(dot+1);
            }
        }
        return ext;
    }

    private String saveFile(MultipartFile file, Long userID){
        String fileName=file.getOriginalFilename();
        String fileExtension=getExtension(fileName);
        String fileHashID=hashids.encode(userID);
        Date now=new Date();
        File folderForUpload=new File(String.format("%s/uploadedFiles/%d/%d/%d/",
                this.uploadFolder,
                1900+now.getYear(),
                1+now.getMonth(),
                now.getDate()));
        if(!folderForUpload.exists() && folderForUpload.mkdirs()){
            System.out.println("Folders created");
        }
        else{
            System.out.println("Folders existed");
        }
        folderForUpload=folderForUpload.getAbsoluteFile();
        File upload=new File(folderForUpload,String.format("%s.%s",
                fileHashID,
                fileExtension));
        try{
            file.transferTo(upload);
            return String.format("/resource/files/uploadedFiles/%d/%d/%d/%s.%s",
                    1900+now.getYear(),
                    1+now.getMonth(),
                    now.getDate(),
                    fileHashID,
                    fileExtension);
        }
        catch(IOException e){
            System.out.println(e);
            return null;
        }
    }
    private void deleteFile(String path){
        File image = new File(downloadFolder + path);
        image.delete();
    }

    public void delete(Long id) {
        Users user = userRepository.findByIDQuery(id);
        if (user.getImagePath()!=null) {
            deleteFile(user.getImagePath());
        }
        if (user.getDocumentPath()!=null) {
           deleteFile(user.getDocumentPath());
        }
        userRepository.delete(user);
    }

    public void updateFirstNameByID(Long userID, String firstName) {
        userRepository.updateFirstNameNative(firstName, userID);
    }
    public void updateLastNameByID(Long userID, String lastName) {
        userRepository.updateLastNameNative(userID, lastName);
    }
    public void updateUserNameByID(Long userID, String userName) {
        userRepository.updateUserNameNative(userID, userName);
    }
    public void updatePasswordByID(Long userID, String password) {
        userRepository.updatePasswordNative(userID, password);
    }
    public void updateBlockedByID(Long userID, Integer blocked) {
        userRepository.updateBlockedNative(userID, blocked);
    }
    public void updateRoleByID(Long userID, String role) {
        userRepository.updateRoleNative(userID, role);
    }
}

package com.code.force.service;


import com.code.force.domain.UsersTime;
import com.code.force.model.UsersReport;
import com.code.force.repository.UsersTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UsersTimeService {

    @Autowired
    private UsersTimeRepository usersTimeRepository;



    public List<UsersTime> getAllUsersTime() {
        return usersTimeRepository.getAllUsersTime();
    }

    public List<UsersTime> getUsersTimeByID(Long ID) {
        return usersTimeRepository.getUserTimeByID(ID);
    }

    public void setUserTime(UsersTime userTime) {
        usersTimeRepository.save(userTime);
    }

    public Integer getUserPresentDays(Long userID) {
        Integer value = usersTimeRepository.getPresentDaysWithJoin(userID);
        if (value == null)
            return 0;
        else
            return value;
    }

    public Integer getUserMissedDays(Long userID) {
        Integer value = usersTimeRepository.getMissedDaysWithJoin(userID);
        if (value == null)
            return 0;
        else
            return value;
    }

    public List<UsersReport> getAllUsersReportOrderedBy(String order) {
        List<UsersReport> usersReports = new ArrayList<>();
        List<String> users;
        if(order==null)
            users= usersTimeRepository.getUsersReportListSortedByPresentDays();
        else if(order.equals("PRESENT"))
            users= usersTimeRepository.getUsersReportListSortedByPresentDays();
        else if(order.equals("MISSED"))
            users=usersTimeRepository.getUsersReportListSortedByMissedDays();
        else if(order.equals("TOTAL"))
            users=usersTimeRepository.getUsersReportListSortedByTotalHours();
        else
            users= usersTimeRepository.getUsersReportListSortedByPresentDays();
        users.stream().forEach(c -> {
            String arr[] = c.split(",");
            UsersReport ur = new UsersReport();
            ur.setUserID(Long.parseLong(arr[0]));
            ur.setFirstName(arr[1]);
            ur.setLastName(arr[2]);
            ur.setPresentDays(Integer.parseInt(arr[3].equals("null")? "0":arr[3]));
            ur.setMissedDays(Integer.parseInt(arr[4].equals("null")? "0":arr[4]));
            ur.setAllHours((int)Double.parseDouble(arr[5].equals("null")? "0":arr[5]));
            usersReports.add(ur);
        });
        return usersReports;
    }

    public Integer getTotalWorkedHours(Long userID) {
        Integer value = usersTimeRepository.getTotalWorkedHoursWithJoin(userID);
        if (value == null)
            return 0;
        else
            return value;
    }
}

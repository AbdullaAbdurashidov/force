package com.code.force.repository;

import com.code.force.domain.UsersTime;
import com.code.force.model.UsersReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface UsersTimeRepository extends JpaRepository<UsersTime, Long> {

    @Query("select u from UsersTime u WHERE u.userID = :userID")
    List<UsersTime> getUserTimeByID(@Param("userID") Long userID);

    @Query("select u from UsersTime u")
    List<UsersTime> getAllUsersTime();

    @Query(value = "select  SUM(\n" +
            "case\n" +
            "\twhen (cast(replace(leaving_time,':','') as double precision)-cast(replace(arrival_time,':','') as double precision))/100>=8 then 1 \n" +
            "end)\n" +
            "as \"'Worked'\" from users_time\n" +
            "where userID=:userID", nativeQuery = true)
    Integer getPresentDaysByID(@Param("userID") Long userID);

    @Query(value = "select  SUM(\n" +
            "case\n" +
            "\twhen (cast(replace(leaving_time,':','') as double precision)-cast(replace(arrival_time,':','') as double precision))/100<8 then 1 \n" +
            "end)\n" +
            "as \"'Worked'\" from users_time\n" +
            "where userID=:userID", nativeQuery = true)
    Integer getMissedDaysByID(@Param("userID") Long userID);

    @Query(value = "select SUM((cast(replace(leaving_time,':','') as double precision)-cast(replace(arrival_time,':','') as double precision))/100)\n" +
            "as \"'Worked'\" from users_time\n" +
            "where userID=:userID", nativeQuery = true)
    Integer getTotalWorkedHours(@Param("userID") Long userID);


    @Query(value = "select sum(cast(replace(ut.leaving_time,':','') as double precision)-cast(replace(ut.arrival_time,':','') as double precision))/100 \n" +
            "from users u\n" +
            "join users_time ut on u.userid=ut.userid\n" +
            "where u.userid=:userID",nativeQuery = true)
    Integer getTotalWorkedHoursWithJoin(@Param("userID") Long userID);


    @Query(value = "select sum(\n" +
            "case\n" +
            "\twhen (cast(replace(ut.leaving_time,':','') as double precision)-cast(replace(ut.arrival_time,':','') as double precision))/100>=8 then 1\n" +
            "end\n" +
            "\t)\n" +
            "from users u\n" +
            "join users_time ut on u.userid=ut.userid\n" +
            "where u.userid=:userID",nativeQuery = true)
    Integer getPresentDaysWithJoin(@Param("userID") Long userID);

    @Query(value = "select sum(\n" +
            "case\n" +
            "\twhen (cast(replace(ut.leaving_time,':','') as double precision)-cast(replace(ut.arrival_time,':','') as double precision))/100<8 then 1\n" +
            "end\n" +
            "\t)\n" +
            "from users u\n" +
            "join users_time ut on u.userid=ut.userid\n" +
            "where u.userid=:userID", nativeQuery = true)
    Integer getMissedDaysWithJoin(@Param("userID") Long userID);


    @Query(value = "select l.userid, l.first_name,l.last_name,y.present_days,q.missed_days,r.all_hours\n" +
            "from users l\n" +
            "join \n" +
            "(\n" +
            "select sum(\n" +
            "case\n" +
            "\twhen (cast(replace(ut.leaving_time,':','') as double precision)-cast(replace(ut.arrival_time,':','') as double precision))/100>=8 then 1\n" +
            "\twhen (cast(replace(ut.leaving_time,':','') as double precision)-cast(replace(ut.arrival_time,':','') as double precision))/100<8 then 0\n" +
            "end\n" +
            "\t) as \"present_days\", u.userid\n" +
            "from users u\n" +
            "join users_time ut on u.userid=ut.userid\n" +
            "\n" +
            "group by u.userid\n" +
            ")as y on l.userid=y.userid\n" +
            "join\n" +
            "(\n" +
            "\tselect sum(\n" +
            "case\n" +
            "\twhen (cast(replace(ut.leaving_time,':','') as double precision)-cast(replace(ut.arrival_time,':','') as double precision))/100<8 then 1\n" +
            "end\n" +
            "\t) as \"missed_days\", u.userid\n" +
            "from users u\n" +
            "join users_time ut on u.userid=ut.userid\n" +
            "\n" +
            "group by u.userid\n" +
            ") as q on l.userid=q.userid\n" +
            "join\n" +
            "(\n" +
            "\tselect sum(\n" +
            "(cast(replace(ut.leaving_time,':','') as double precision)-cast(replace(ut.arrival_time,':','') as double precision))/100\n" +
            "\t) as \"all_hours\", u.userid\n" +
            "from users u\n" +
            "join users_time ut on u.userid=ut.userid\n" +
            "\n" +
            "group by u.userid\n" +
            ") as r on l.userid=r.userid\n" +
            "where l.role='STUDENT'\n"+
            "order by y.present_days desc",nativeQuery = true)
    List<String> getUsersReportListSortedByPresentDays();


    @Query(value = "select l.userid, l.first_name,l.last_name,y.present_days,q.missed_days,r.all_hours\n" +
            "from users l\n" +
            "join \n" +
            "(\n" +
            "select sum(\n" +
            "case\n" +
            "\twhen (cast(replace(ut.leaving_time,':','') as double precision)-cast(replace(ut.arrival_time,':','') as double precision))/100>=8 then 1\n" +
            "\twhen (cast(replace(ut.leaving_time,':','') as double precision)-cast(replace(ut.arrival_time,':','') as double precision))/100<8 then 0\n" +
            "end\n" +
            "\t) as \"present_days\", u.userid\n" +
            "from users u\n" +
            "join users_time ut on u.userid=ut.userid\n" +
            "\n" +
            "group by u.userid\n" +
            ")as y on l.userid=y.userid\n" +
            "join\n" +
            "(\n" +
            "\tselect sum(\n" +
            "case\n" +
            "\twhen (cast(replace(ut.leaving_time,':','') as double precision)-cast(replace(ut.arrival_time,':','') as double precision))/100<8 then 1\n" +
            "\twhen (cast(replace(ut.leaving_time,':','') as double precision)-cast(replace(ut.arrival_time,':','') as double precision))/100>=8 then 0\n" +
            "end\n" +
            "\t) as \"missed_days\", u.userid\n" +
            "from users u\n" +
            "join users_time ut on u.userid=ut.userid\n" +
            "\n" +
            "group by u.userid\n" +
            ") as q on l.userid=q.userid\n" +
            "join\n" +
            "(\n" +
            "\tselect sum(\n" +
            "(cast(replace(ut.leaving_time,':','') as double precision)-cast(replace(ut.arrival_time,':','') as double precision))/100\n" +
            "\t) as \"all_hours\", u.userid\n" +
            "from users u\n" +
            "join users_time ut on u.userid=ut.userid\n" +
            "\n" +
            "group by u.userid\n" +
            ") as r on l.userid=r.userid\n" +
            "where l.role='STUDENT'\n" +
            "order by q.missed_days desc",nativeQuery = true)
    List<String> getUsersReportListSortedByMissedDays();

    @Query(value = "select l.userid, l.first_name,l.last_name,y.present_days,q.missed_days,r.all_hours\n" +
            "from users l\n" +
            "join \n" +
            "(\n" +
            "select sum(\n" +
            "case\n" +
            "\twhen (cast(replace(ut.leaving_time,':','') as double precision)-cast(replace(ut.arrival_time,':','') as double precision))/100>=8 then 1\n" +
            "\twhen (cast(replace(ut.leaving_time,':','') as double precision)-cast(replace(ut.arrival_time,':','') as double precision))/100<8 then 0\n" +
            "end\n" +
            "\t) as \"present_days\", u.userid\n" +
            "from users u\n" +
            "join users_time ut on u.userid=ut.userid\n" +
            "\n" +
            "group by u.userid\n" +
            ")as y on l.userid=y.userid\n" +
            "join\n" +
            "(\n" +
            "\tselect sum(\n" +
            "case\n" +
            "\twhen (cast(replace(ut.leaving_time,':','') as double precision)-cast(replace(ut.arrival_time,':','') as double precision))/100<8 then 1\n" +
            "end\n" +
            "\t) as \"missed_days\", u.userid\n" +
            "from users u\n" +
            "join users_time ut on u.userid=ut.userid\n" +
            "\n" +
            "group by u.userid\n" +
            ") as q on l.userid=q.userid\n" +
            "join\n" +
            "(\n" +
            "\tselect sum(\n" +
            "(cast(replace(ut.leaving_time,':','') as double precision)-cast(replace(ut.arrival_time,':','') as double precision))/100\n" +
            "\t) as \"all_hours\", u.userid\n" +
            "from users u\n" +
            "join users_time ut on u.userid=ut.userid\n" +
            "\n" +
            "group by u.userid\n" +
            ") as r on l.userid=r.userid\n" +
            "where l.role='STUDENT'\n"+
            "order by r.all_hours desc",nativeQuery = true)
    List<String> getUsersReportListSortedByTotalHours();
}

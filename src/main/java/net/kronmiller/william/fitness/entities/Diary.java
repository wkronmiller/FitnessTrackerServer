package net.kronmiller.william.fitness.entities;

import java.sql.Date;
import java.util.List;

/**
 * Created by wkronmiller on 7/16/17.
 */
public class Diary implements Entity {
    private Date startDate, endDate;
    private List<DiaryDay> diaryDayList;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<DiaryDay> getDiaryDayList() {
        return diaryDayList;
    }

    public void setDiaryDayList(List<DiaryDay> diaryDayList) {
        this.diaryDayList = diaryDayList;
    }
}

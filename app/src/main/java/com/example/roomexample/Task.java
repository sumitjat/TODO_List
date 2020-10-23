package com.example.roomexample;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Task implements Serializable
{
        @PrimaryKey(autoGenerate = true)
        private  int id;

        @ColumnInfo(name = "task")
        private String task;

    @ColumnInfo(name = "desc")
    private String desc;

    @ColumnInfo(name = "finishby")
    private String finsishby;

    @ColumnInfo(name = "finished")
    private boolean  finished;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFinsishby() {
        return finsishby;
    }

    public void setFinsishby(String finsishby) {
        this.finsishby = finsishby;
    }

    public boolean getFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}

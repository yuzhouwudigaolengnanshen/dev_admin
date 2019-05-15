package com.dev.admin.vo;

import com.dev.admin.entity.Log;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 日志视图对象
 * Created by yuboon on 2019/02/24
 */
public class LogVo extends Log {

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startCreateDatetime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endCreateDatetime;

    public Date getStartCreateDatetime() {
        return startCreateDatetime;
    }

    public void setStartCreateDatetime(Date startCreateDatetime) {
        this.startCreateDatetime = startCreateDatetime;
    }

    public Date getEndCreateDatetime() {
        return endCreateDatetime;
    }

    public void setEndCreateDatetime(Date endCreateDatetime) {
        this.endCreateDatetime = endCreateDatetime;
    }
}

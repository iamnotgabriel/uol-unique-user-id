package org.fatec.uniqueuserid.users.controller.dto;

import java.sql.Timestamp;
import java.util.List;

public class UserCreationDTO {
    public String name;
    public String email;
    public String phone;
    public String password;
    public String deviceId;
    public Integer pasteCount;
    public Timestamp startTime;
    public Timestamp endTime;
    public String os;
    public String ip;
    public String browser;
    public Integer screenHeight;
    public Integer screenWidth;
    public String timezone;
    public Short cpuCores;
    public String gpuName;
    public List<Integer> keyUps;
    public List<Integer> keyDowns;
}

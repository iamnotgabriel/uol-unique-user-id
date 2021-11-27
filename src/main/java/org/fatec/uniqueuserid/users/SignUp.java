package org.fatec.uniqueuserid.users;

import com.fasterxml.jackson.annotation.JsonView;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.fatec.uniqueuserid.users.controller.dto.UserCreationDTO;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "signup_metadata")
@TypeDef(name = "json", typeClass = JsonBinaryType.class)
public class SignUp {
    @Id
    @Column(name="signup_md_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "signup_md_device_id")
    @JsonView(User.UserData.class)
    private String deviceId = "not collected";

    @Column(name="signup_md_start_date")
    @JsonView(User.UserData.class)
    private Timestamp startTime = new Timestamp(0);

    @Column(name="signup_md_end_date")
    @JsonView(User.UserData.class)
    private Timestamp endTime = new Timestamp(0);;

    @Column(name="signup_md_timezone")
    @JsonView(User.UserData.class)
    private String timezone = "UTC";

    @Column(name="signup_md_ip")
    @JsonView(User.UserData.class)
    private String ip = "127.0.0.1";

    @Column(name="signup_md_paste_count")
    @JsonView(User.UserData.class)
    private Integer pasteCount = 0;

    @Column(name="signup_md_os")
    @JsonView(User.UserData.class)
    private String os = "not collected";

    @Column(name="signup_md_screen_width")
    @JsonView(User.UserData.class)
    private Integer screenWidth =  0;

    @Column(name="signup_md_screen_height")
    @JsonView(User.UserData.class)
    private Integer screenHeight = 0;

    @Column(name="signup_md_device_name")
    @JsonView(User.UserData.class)
    private String deviceName = "not collected";

    @Column(name="signup_md_browser")
    @JsonView(User.UserData.class)
    private String browser = "not collected";

    @Column(name="signup_md_cpu_cores")
    @JsonView({User.UserData.class})
    private Short cpuCores;

    @Column(name="signup_md_gpu_name")
    @JsonView({User.UserData.class})
    private String gpuName;

    @Column(name="signup_md_key_ups", columnDefinition = "json")
    @Type(type = "json")
    @JsonView({User.UserData.class})
    private List<Integer> keyUps;

    @Column(name="signup_md_key_downs", columnDefinition = "json")
    @Type(type = "json")
    @JsonView({User.UserData.class})
    private List<Integer> keyDowns;

    public SignUp() {}

    public static SignUp create(UserCreationDTO dto, User user) {
        SignUp signUp = new SignUp();
        signUp.deviceId = dto.deviceId;
        signUp.startTime = dto.startTime;
        signUp.endTime =  dto.endTime;
        signUp.pasteCount = dto.pasteCount;
        signUp.user = user;
        signUp.browser = dto.browser;
        signUp.ip = dto.ip;
        signUp.screenHeight = dto.screenHeight;
        signUp.screenWidth = dto.screenWidth;
        signUp.os = dto.os;
        signUp.timezone = dto.timezone;
        signUp.cpuCores = dto.cpuCores;
        signUp.gpuName = dto.gpuName;
        signUp.setKeyUps(dto.keyUps);
        signUp.setKeyDowns(dto.keyDowns);
        return signUp;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public Integer getPasteCount() {
        return pasteCount;
    }

    public String getOs() {
        return os;
    }

    public Integer getScreenWidth() {
        return screenWidth;
    }

    public Integer getScreenHeight() {
        return screenHeight;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public Short getCpuCores() {
        return cpuCores;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public void setCpuCores(Short cpuCores) {
        this.cpuCores = cpuCores;
    }

    public String getGpuName() {
        return gpuName;
    }

    public void setGpuName(String gpuName) {
        this.gpuName = gpuName;
    }

    public String getBrowser() {
        return browser;
    }

    public List<Integer> getKeyUps() {
        return keyUps;
    }

    public void setKeyUps(List<Integer> keyUps) {
        this.keyUps = normalize(keyUps);
    }

    public List<Integer> getKeyDowns() {
        return keyDowns;
    }

    public void setKeyDowns(List<Integer> keyDowns) {
        this.keyDowns = normalize(keyDowns);
    }

    private List<Integer> normalize(List<Integer> l) {
        int diff = 6 - l.size();
        if (diff > 0) {
            int n = l.get(l.size() - 1);
            for(int i=0; i < diff; i++) {
                l.add(n);
            }
        } else if (diff < 0) {
            for(int i=0; i>diff; i--) {
                l.remove(0);
            }
        }
        return l;
    }

}

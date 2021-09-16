package org.fatec.uniqueuserid.users;

import org.fatec.uniqueuserid.users.controller.dto.UserCreationDTO;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "signup_metadata")
public class SignUp {
    @Id
    @Column(name="signup_md_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "signup_md_device_id")
    private String deviceId;

    @Column(name="signup_md_start_date")
    private Timestamp startTime;

    @Column(name="signup_md_end_date")
    private Timestamp endTime;

    @Column(name="signup_md_timezone")
    private String timezone = "UTC";

    @Column(name="signup_md_ip")
    private String ip = "127.0.0.1";

    @Column(name="signup_md_paste_count")
    private Integer pasteCount;

    @Column(name="signup_md_os")
    private String os = "not collected";

    @Column(name="signup_md_screen_width")
    private Integer screenWidth =  0;

    @Column(name="signup_md_screen_height")
    private Integer screenHeight = 0;

    @Column(name="signup_md_device_name")
    private String deviceName = "not collected";

    @Column(name="signup_md_browser")
    private String browser = "not collected";

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

    public String getBrowser() {
        return browser;
    }
}

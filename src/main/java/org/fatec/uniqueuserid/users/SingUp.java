package org.fatec.uniqueuserid.users;

import org.fatec.uniqueuserid.users.controller.dto.UserCreationDTO;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "signup_metadata")
public class SingUp {
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
    private String browserName = "not collected";

    public SingUp() {}

    public static SingUp create(UserCreationDTO dto, User user) {
        SingUp singUp = new SingUp();
        singUp.deviceId = dto.deviceId;
        singUp.startTime = dto.startTime;
        singUp.endTime =  dto.endTime;
        singUp.pasteCount = dto.pasteCount;
        singUp.user = user;
        return singUp;
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

    public String getBrowserName() {
        return browserName;
    }
}

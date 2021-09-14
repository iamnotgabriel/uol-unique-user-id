package org.fatec.uniqueuserid.users;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;

@Entity
@Table(name = "signup_metadata")
public class SingUp {
    @Id
    @Column(name="signup_md_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "signup_md_device_id")
    private String deviceId;

    @Column(name="signup_md_start_date")
    private Timestamp startDate;

    @Column(name="signup_md_end_date")
    private Timestamp endDate;

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
}

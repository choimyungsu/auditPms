package pms;

public class AuditHistory {

    private Integer audithistoryid;
    
    private String userid;

    private Integer projectid;

    private String audityearmonth;

    private String auditname;

    private String mainclient;

    private String maindivision;

    private String auditfield;

    private String auditrole;

    private String joinrate;

    public Integer getAudithistoryid() {
        return audithistoryid;
    }

    public void setAudithistoryid(Integer audithistoryid) {
        this.audithistoryid = audithistoryid;
    }

    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    
    
    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getAudityearmonth() {
        return audityearmonth;
    }

    public void setAudityearmonth(String audityearmonth) {
        this.audityearmonth = audityearmonth;
    }

    public String getAuditname() {
        return auditname;
    }

    public void setAuditname(String auditname) {
        this.auditname = auditname;
    }

    public String getMainclient() {
        return mainclient;
    }

    public void setMainclient(String mainclient) {
        this.mainclient = mainclient;
    }

    public String getMaindivision() {
        return maindivision;
    }

    public void setMaindivision(String maindivision) {
        this.maindivision = maindivision;
    }

    public String getAuditfield() {
        return auditfield;
    }

    public void setAuditfield(String auditfield) {
        this.auditfield = auditfield;
    }

    public String getAuditrole() {
        return auditrole;
    }

    public void setAuditrole(String auditrole) {
        this.auditrole = auditrole;
    }

    public String getJoinrate() {
        return joinrate;
    }

    public void setJoinrate(String joinrate) {
        this.joinrate = joinrate;
    }

    // Audithistory ¸ðµ¨ º¹»ç
    public void CopyData(AuditHistory param)
    {
        this.audithistoryid = param.getAudithistoryid();
        this.projectid = param.getProjectid();
        this.audityearmonth = param.getAudityearmonth();
        this.auditname = param.getAuditname();
        this.mainclient = param.getMainclient();
        this.maindivision = param.getMaindivision();
        this.auditfield = param.getAuditfield();
        this.auditrole = param.getAuditrole();
        this.joinrate = param.getJoinrate();
    }
}
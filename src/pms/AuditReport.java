package pms;

import java.sql.Timestamp;

public class AuditReport {

    private Integer auditreportid;

    private Integer projectid;

    private String seq;

    private String auditname;

    private String placeauditdate;

    private String auditors;

    private String auditfield;

    private String contractauditdate;

    private String mainclient;

    private String developcompany;

    private String auditcost;

    private String developcost;

    private String developmethod;

    private String bizoverview;

    private String bizscope;

    private String bizperiod;

    private String maintechnology;

    private Integer auditavailable;

    private Timestamp auditstartdate;

    private Timestamp auditenddate;

    private String mainauditor;

    private String auditstep;

    private Timestamp createdate;

    private Timestamp updatedate;

    public Integer getAuditreportid() {
        return auditreportid;
    }

    public void setAuditreportid(Integer auditreportid) {
        this.auditreportid = auditreportid;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getAuditname() {
        return auditname;
    }

    public void setAuditname(String auditname) {
        this.auditname = auditname;
    }

    public String getPlaceauditdate() {
        return placeauditdate;
    }

    public void setPlaceauditdate(String placeauditdate) {
        this.placeauditdate = placeauditdate;
    }

    public String getAuditors() {
        return auditors;
    }

    public void setAuditors(String auditors) {
        this.auditors = auditors;
    }

    public String getAuditfield() {
        return auditfield;
    }

    public void setAuditfield(String auditfield) {
        this.auditfield = auditfield;
    }

    public String getContractauditdate() {
        return contractauditdate;
    }

    public void setContractauditdate(String contractauditdate) {
        this.contractauditdate = contractauditdate;
    }

    public String getMainclient() {
        return mainclient;
    }

    public void setMainclient(String mainclient) {
        this.mainclient = mainclient;
    }

    public String getDevelopcompany() {
        return developcompany;
    }

    public void setDevelopcompany(String developcompany) {
        this.developcompany = developcompany;
    }

    public String getAuditcost() {
        return auditcost;
    }

    public void setAuditcost(String auditcost) {
        this.auditcost = auditcost;
    }

    public String getDevelopcost() {
        return developcost;
    }

    public void setDevelopcost(String developcost) {
        this.developcost = developcost;
    }

    public String getDevelopmethod() {
        return developmethod;
    }

    public void setDevelopmethod(String developmethod) {
        this.developmethod = developmethod;
    }

    public String getBizoverview() {
        return bizoverview;
    }

    public void setBizoverview(String bizoverview) {
        this.bizoverview = bizoverview;
    }

    public String getBizscope() {
        return bizscope;
    }

    public void setBizscope(String bizscope) {
        this.bizscope = bizscope;
    }

    public String getBizperiod() {
        return bizperiod;
    }

    public void setBizperiod(String bizperiod) {
        this.bizperiod = bizperiod;
    }

    public String getMaintechnology() {
        return maintechnology;
    }

    public void setMaintechnology(String maintechnology) {
        this.maintechnology = maintechnology;
    }

    public Integer getAuditavailable() {
        return auditavailable;
    }

    public void setAuditavailable(Integer auditavailable) {
        this.auditavailable = auditavailable;
    }

    public Timestamp getAuditstartdate() {
        return auditstartdate;
    }

    public void setAuditstartdate(Timestamp auditstartdate) {
        this.auditstartdate = auditstartdate;
    }

    public Timestamp getAuditenddate() {
        return auditenddate;
    }

    public void setAuditenddate(Timestamp auditenddate) {
        this.auditenddate = auditenddate;
    }

    public String getMainauditor() {
        return mainauditor;
    }

    public void setMainauditor(String mainauditor) {
        this.mainauditor = mainauditor;
    }

    public String getAuditstep() {
        return auditstep;
    }

    public void setAuditstep(String auditstep) {
        this.auditstep = auditstep;
    }

    public Timestamp getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
    }

    public Timestamp getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Timestamp updatedate) {
        this.updatedate = updatedate;
    }

    // Auditreport ¸ðµ¨ º¹»ç
    public void CopyData(AuditReport param)
    {
        this.auditreportid = param.getAuditreportid();
        this.projectid = param.getProjectid();
        this.seq = param.getSeq();
        this.auditname = param.getAuditname();
        this.placeauditdate = param.getPlaceauditdate();
        this.auditors = param.getAuditors();
        this.auditfield = param.getAuditfield();
        this.contractauditdate = param.getContractauditdate();
        this.mainclient = param.getMainclient();
        this.developcompany = param.getDevelopcompany();
        this.auditcost = param.getAuditcost();
        this.developcost = param.getDevelopcost();
        this.developmethod = param.getDevelopmethod();
        this.bizoverview = param.getBizoverview();
        this.bizscope = param.getBizscope();
        this.bizperiod = param.getBizperiod();
        this.maintechnology = param.getMaintechnology();
        this.auditavailable = param.getAuditavailable();
        this.auditstartdate = param.getAuditstartdate();
        this.auditenddate = param.getAuditenddate();
        this.mainauditor = param.getMainauditor();
        this.auditstep = param.getAuditstep();
        this.createdate = param.getCreatedate();
        this.updatedate = param.getUpdatedate();
    }
}
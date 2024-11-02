package StaffManage;

public class StaffRecords {
    private int staffID;
    private String name;
    private String role;
    private String gender;
    private int age;
    
    public void setID(int ID) {this.staffID = ID;}
    public void setname(String name) {this.name = name;}
    public void setrole(String role) {this.role = role;}
    public void setgender(String gender) {this.gender = gender;}
    public void setage(int age) {this.age = age;}

    public int getID() {return staffID;}
    public String getname() {return name;}
    public String getrole() {return role;}
    public String getgender() {return gender;}
    public int getage() {return age;}
}
